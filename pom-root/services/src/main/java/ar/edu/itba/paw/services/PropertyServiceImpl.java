package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Service
public class PropertyServiceImpl implements PropertyService {

	private final int PAGE_SIZE = 10;
	private final int FIRST_PAGE = 1;

	@Autowired
	private PropertyDao propertyDao;

	public List<Property> getAll(){
		return propertyDao.getAll();
	}

	public Optional<Property> getPropertyById(long id){
		return propertyDao.getById(id);
	}

	public Boolean propertyExists(long id){
		return getPropertyById(id).isPresent();
	}

	public List<Property> getAllByUserId(final long id){
		return propertyDao.getAllByUserId(id);
	}
	
	public Long createProperty(String street, Integer floor, String apartment, PropertyType type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {
	    return propertyDao.createProperty(street, floor, apartment, type, userId, price, coveredArea, totalArea, rooms, baths, garage, taxPrice);
	}

	public Long createProperty(String street, Integer floor, String apartment, PropertyType type, Long userId, Long price) {
		return propertyDao.createProperty(street, floor, apartment, type, userId, price, null, null, null, null, null, null);
	}

	public Long createProperty(String street, Integer floor, String apartment, String neighborhood, OperationType operationType, PropertyType type, User publisherUser, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery) {
	    Map<String, Integer> tags = new HashMap<String, Integer>();
		Arrays.stream(street.trim().toLowerCase().split("\\s+")).forEach(
				word -> tags.put( word, FilterType.street.ordinal())
		);
		Arrays.stream(neighborhood.trim().toLowerCase().split("\\s+")).forEach(
				word -> tags.put( word, FilterType.neighborhood.ordinal())
		);
	    tags.put(operationType.toString(), FilterType.operationType.ordinal());
	    tags.put(type.toString(), FilterType.type.ordinal());
	    tags.put(rooms.toString(), FilterType.rooms.ordinal());
	    tags.put(baths.toString(), FilterType.baths.ordinal());
	    if (garage)
		    tags.put("garage", FilterType.garage.ordinal());
	    if (inmediateDelivery)
		    tags.put("imediate_delivery", FilterType.inmediateDelivery.ordinal());
		return propertyDao.createProperty(street, floor, apartment, neighborhood, operationType, type, publisherUser, price, coveredArea, totalArea, rooms, baths, garage, taxPrice, adMessage, adDescription, inmediateDelivery, tags);
	}



	public Property findById(final long id){
		return propertyDao.findById(id);
	}

	private static final Map<String,String> filterStringMap;
	private static final Map<String,String> filterIntMap;
	private static final Map<String,String> filterBoolMap;
	private static final Map<String,String> orderMap;
	static {
		Map<String,String> tmpMap = new HashMap<String,String>();
		Map<String,String> tmpMap2 = new HashMap<String,String>();
		Map<String,String> tmpMap3 = new HashMap<String,String>();
		Map<String,String> tmpMap4 = new HashMap<String,String>();

		tmpMap.put("type", "type=");
		tmpMap.put("neighborhood", "neighborhood=");

		tmpMap2.put("maxArea", "total_area<=");
		tmpMap2.put("minArea", "total_area>=");
		tmpMap2.put("rooms", "rooms=");
		tmpMap2.put("bath", "baths=");
		tmpMap2.put("maxPrice", "price<=");
		tmpMap2.put("minPrice", "price>=");

		tmpMap3.put("garage", "garage=");
		
		//deberia separar tipo de forma (asc, desc)
		tmpMap4.put("price_asc","p.price asc");
		tmpMap4.put("price_desc","p.price desc");
		tmpMap4.put("date_asc","p.ad_date asc");
		tmpMap4.put("date_desc","p.ad_date desc");

		filterIntMap = Collections.unmodifiableMap(tmpMap2);
		filterStringMap = Collections.unmodifiableMap(tmpMap);
		filterBoolMap = Collections.unmodifiableMap(tmpMap3);
		orderMap = Collections.unmodifiableMap(tmpMap4);

	}

	public List<Property> getFiltered(Map<String,String> filters){

		ArrayList<Object> params =new ArrayList();
		StringBuilder query = new StringBuilder(200);
		String order=null;


		if(filters.containsKey("order_by")) {
			if (orderMap.containsKey(filters.get("order_by")))
				order = orderMap.get(filters.get("order_by"));
			else return Collections.emptyList();
			filters.remove("order_by");
		}else order="p.ad_date ASC";
		if(filters.containsKey("language"))
			filters.remove("language");

		int date=removeFilters(filters,query,params);

		int i = filters.size();
		if(i>filterIntMap.size()+filterStringMap.size()+filterBoolMap.size()) return Collections.emptyList();


		for (Map.Entry<String, String> entry : filters.entrySet()) {
			if(filterStringMap.containsKey(entry.getKey())) {
				query.append(filterStringMap.get(entry.getKey()));
				query.append("?");
				params.add(entry.getValue());
			}else {
				if (filterIntMap.containsKey(entry.getKey())) {
					query.append(filterIntMap.get(entry.getKey()));
					query.append("?");
					params.add(Integer.parseInt(entry.getValue()));
				} else{
					if (filterBoolMap.containsKey(entry.getKey())) {
						query.append(filterBoolMap.get(entry.getKey()));
						query.append("?");
						params.add(Boolean.parseBoolean(entry.getValue()));
					}else  return Collections.emptyList();
				}
			}

			i--;
			if(i>0)
				query.append(" AND ");
		}

		List<Property> propertiesList= propertyDao.getFiltered(query.toString(), params, order);
		if(date>0){
			propertiesList=removeOld(propertiesList,date);
		}
		return propertiesList;
	}

	private int removeFilters(Map<String,String> filters, StringBuilder query,ArrayList<Object> params ){
		query.append("operation_type=?");
		if(!filters.containsKey("operation")) {
			params.add("sell");
		}else {
			params.add(filters.get("operation"));
			filters.remove("operation");
		}

		int date=-1;
		if(filters.containsKey("date")) {
			date = Integer.parseInt(filters.get("date"));
			filters.remove("date");
		}

		if(!filters.isEmpty())
			query.append(" AND ");

		return date;
	}

	private List<Property> removeOld(List<Property> propertiesList, int date){
		List<Property> aux =new LinkedList<>();
		for (Property property: propertiesList){
			if(property.getAdDate()<=date)
				aux.add(property);
		}
		return aux;
	}

	/*
		Get property's that matches tags (such as property type, neighborhood, etc.)
	 */
	public List<Property> getPropertiesByTagsSearch(String search){
		return propertyDao.getByTags(
				Arrays.asList(search.split("\\s+"))
				.stream().filter(tag -> propertyDao.getAllTags().contains(tag))
				.collect(Collectors.toList())
		);
	}
	
    public void linkImage(String imageSrc, long propertyId) {
    	propertyDao.addImage(imageSrc, propertyId);
    }

	public List<Property> getPropertiesByTagsSearch(List<String> tags){
		return propertyDao.getByTags(tags);
	}

	public List<String> getAllTags(){
		return propertyDao.getAllTags();
	}

	@Override
	public Map<Integer, Map<String, Long>>  getPotentialFilters(List<Property> list) {
		Map<Integer, Map<String,Long>> map = new HashMap<Integer, Map<String, Long>>();
		Map<String,Long> neighborhoods = list.stream().
				map(Property::getNeighborhood).
				filter(s -> s != null).
				map(s -> s.toLowerCase()).
				collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		Map<String,Long> type = list.stream().
				map(Property::getType).
				map(propertyType -> propertyType.name()).
				filter(s -> s != null).
				collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		Map<String,Long> baths = list.stream().
				map(Property::getBaths).
				map(bath -> bath.toString()).
				filter(s -> s != null).
				collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		Map<String,Long> rooms = list.stream().
				map(Property::getRooms).
				map(room -> room.toString()).
				filter(s -> s != null).
				collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		map.put(1,neighborhoods);
		map.put(3,type);
		map.put(4,baths);
		map.put(5,rooms);
		return map;
	}

	public Map<String, Long> getPropertiesDateBreakdown(List<Property> list){
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("index/filters/published/today", list.stream().filter(property -> property.getAdDate() == 0).count());
		map.put("index/filters/published/last-week", list.stream().filter(property -> property.getAdDate() <= 7  && property.getAdDate() > 0).count());
		map.put("index/filters/published/last-two-weeks", list.stream().filter(property -> property.getAdDate() <= 14  && property.getAdDate() > 0).count());
		map.put("index/filters/published/last-month", list.stream().filter(property -> property.getAdDate() <= 30  && property.getAdDate() > 0).count());
		return map;
	}
	
	public Map<String, String> getShowableFilters(Map<String,String> m){
		
		List<String> hiddenFilters = Arrays.asList("operation", "page", "order_by");
		
		return m.entrySet().stream()
				.filter(entry -> !hiddenFilters.contains(entry.getKey()))
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
	}
	
	public Map<String, String> getFiltrableFields(Map<String,String> m){
		
		List<String> unfiltrableFields = Arrays.asList("page");
		
		return m.entrySet().stream()
				.filter(entry -> !unfiltrableFields.contains(entry.getKey()))
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
	}
}
