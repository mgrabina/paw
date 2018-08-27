package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyDao propertyDao;

	public List<Property> getAll(){
		return propertyDao.getAll();
	}

	@Override
	public Long createProperty(String street, Integer number, Integer floor, String apartment, Property.Type type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {
		return propertyDao.createProperty(street, number, floor, apartment, type, userId, price, coveredArea, totalArea, rooms, baths, garage, taxPrice);
	}

	@Override
	public Long createProperty(String street, Integer number, Integer floor, String apartment, Property.Type type, Long userId, Long price) {
		return propertyDao.createProperty(street, number, floor, apartment, type, userId, price, null, null, null, null, null, null);
	}

	@Override
	public List<Property> getFavorites(Long userId) {
		return propertyDao.getFavorites(userId);
	}

	public Property findById(final long id){
		return propertyDao.findById(id);
	}

}
