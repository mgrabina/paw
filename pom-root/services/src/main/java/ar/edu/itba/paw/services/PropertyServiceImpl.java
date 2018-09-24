package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
		tmpMap.put("operation", "operation_type=");
		tmpMap.put("neighborhood", "neighborhood=");

		tmpMap2.put("maxArea", "total_area<=");
		tmpMap2.put("minArea", "total_area>=");
		tmpMap2.put("rooms", "rooms=");
		tmpMap2.put("bath", "baths=");
		tmpMap2.put("maxPrice", "price<=");
		tmpMap2.put("minPrice", "price>=");

		tmpMap3.put("garage", "garage=");

		tmpMap4.put("price_asd","property.price asc");
		tmpMap4.put("price_desc","property.price desc");
		tmpMap4.put("date_asd","property.ad_date asc");
		tmpMap4.put("date_desc","property.ad_date desc");

		filterIntMap = Collections.unmodifiableMap(tmpMap2);
		filterStringMap = Collections.unmodifiableMap(tmpMap);
		filterBoolMap = Collections.unmodifiableMap(tmpMap3);
		orderMap = Collections.unmodifiableMap(tmpMap4);

	}

	public List<Property> getFiltered(Map<String,String> filters){
		if(filters.isEmpty())
			return getAll();
		ArrayList<Object> params =new ArrayList();
		StringBuilder query = new StringBuilder(200);
		String order=null;
		int i = filters.size();
		int date=-1;
		if(filters.containsKey("date")) {
			date = Integer.parseInt(filters.get("date"));
			filters.remove("date");
		}

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
					}else {
						if(entry.getKey()=="order_by"){
							if(orderMap.containsKey(entry.getValue()))
								order=orderMap.get(entry.getValue());
						} else return Collections.emptyList();
					}
				}
			}
			i--;
			if(i>0)
				query.append(" AND ");
		}
		if (order==null)
			order="property.id ASC";
		List<Property> propertiesList= propertyDao.getFiltered(query.toString(), params, order);
		if(date>0){
			List<Property> aux =new LinkedList<>();
			for (Property property: propertiesList){
				if(property.getAdDate()<=date)
					aux.add(property);
			}
			propertiesList=aux;
		}
		return propertiesList;
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
	public Map<Integer, Map<String, Integer>>  getPotentialFilters() {
		return propertyDao.getPotentialFilters();
	}

	public Map<String, Long> getPropertiesDateBreakdown(List<Property> list){
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("index/filters/published/today", list.stream().filter(property -> property.getAdDate() == 0).count());
		map.put("index/filters/published/last-week", list.stream().filter(property -> property.getAdDate() <= 7  && property.getAdDate() > 0).count());
		map.put("index/filters/published/last-two-weeks", list.stream().filter(property -> property.getAdDate() <= 14  && property.getAdDate() > 0).count());
		map.put("index/filters/published/last-month", list.stream().filter(property -> property.getAdDate() <= 30  && property.getAdDate() > 0).count());
		return map;
	}
}
