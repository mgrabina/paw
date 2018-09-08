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

import java.util.Collections;
import java.util.List;

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

	public Property findById(final long id){
		return propertyDao.findById(id);
	}

}
