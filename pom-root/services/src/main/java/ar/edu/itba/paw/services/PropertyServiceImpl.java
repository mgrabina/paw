package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	private final int PAGE_SIZE = 10;
	private final int FIRST_PAGE = 1;

	
	@Autowired
	private PropertyDao propertyDao;

	public List<Property> getAll(){
		return propertyDao.getAll();
	}
	
	public List<Property> getPage(List<Property> propertiesList, int pageNumber) throws IllegalArgumentException{
		
		if(pageNumber <= 0) {
	        throw new IllegalArgumentException("Invalid page number: " + pageNumber);
	    }

	    int from = (pageNumber - 1) * PAGE_SIZE;
	    
	    if(propertiesList == null || propertiesList.size() < from){
	        return Collections.emptyList();
	    }

	    // toIndex exclusive
	    return propertiesList.subList(from, Math.min(from + PAGE_SIZE, propertiesList.size()));
		
	}
	
    public List<Property> getPage(final List<Property> propertiesList){
    	return getPage(propertiesList, FIRST_PAGE);
    }
	
	public int getPageCount(List<Property> propertiesList) {
		
		 if(propertiesList == null)
			 return 0;
		 
		 return Math.round(propertiesList.size() / PAGE_SIZE);
	}
	
	public Long createProperty(String street, Integer number, Integer floor, String apartment, PropertyType type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {
		return propertyDao.createProperty(street, number, floor, apartment, type, userId, price, coveredArea, totalArea, rooms, baths, garage, taxPrice);
	}

	public Long createProperty(String street, Integer number, Integer floor, String apartment, PropertyType type, Long userId, Long price) {
		return propertyDao.createProperty(street, number, floor, apartment, type, userId, price, null, null, null, null, null, null);
	}

	public Long createProperty(String street, Integer number, Integer floor, String apartment, String neighborhood, OperationType operationType, PropertyType type, User publisherUser, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery) {
		return propertyDao.createProperty(street, number, floor, apartment, neighborhood, operationType, type, publisherUser, price, coveredArea, totalArea, rooms, baths, garage, taxPrice, adMessage, adDescription, inmediateDelivery);
	}

	@Override
	public List<Property> getFavourites(Long userId) {
		return propertyDao.getFavourites(userId);
	}

	@Override
	public void setFavourite(Long userId, Long propertyId) {
		propertyDao.setFavourite(userId, propertyId);
	}

	@Override
	public void deleteFavourite(Long userId, Long propertyId) {
		propertyDao.deleteFavourite(userId, propertyId);
	}

	public Property findById(final long id){
		return propertyDao.findById(id);
	}

	private static final Map<String,String> filterStringMap;
	private static final Map<String,String> filterIntMap;
	private static final Map<String,String> filterBoolMap;
	static {
		Map<String,String> tmpMap = new HashMap<String,String>();
		Map<String,String> tmpMap2 = new HashMap<String,String>();
		Map<String,String> tmpMap3 = new HashMap<String,String>();
		tmpMap.put("type", "type=");
		tmpMap2.put("maxPrice", "price<=");
		tmpMap2.put("minPrice", "price>=");
		tmpMap.put("operation", "operation_type=");
		tmpMap2.put("maxArea", "total_area<=");
		tmpMap2.put("minArea", "total_area>=");
		tmpMap2.put("rooms", "rooms=");
		tmpMap2.put("bath", "baths=");
		tmpMap3.put("garage", "garage=");

		filterIntMap = Collections.unmodifiableMap(tmpMap2);
		filterStringMap = Collections.unmodifiableMap(tmpMap);
		filterBoolMap = Collections.unmodifiableMap(tmpMap3);
	}

	public List<Property> getFiltered(Map<String,String> filters){
		if(filters.isEmpty())
			return getAll();
		ArrayList<Object> params =new ArrayList();
		StringBuilder query = new StringBuilder(200);
		int i = filters.size();
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
					}else return Collections.emptyList();
				}
			}
			i--;
			if(i>0)
				query.append(" AND ");
		}
		return propertyDao.getFiltered(query.toString(), params);
	}



}
