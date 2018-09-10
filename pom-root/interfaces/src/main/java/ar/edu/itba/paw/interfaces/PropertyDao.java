package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.User;

import java.util.ArrayList;
import java.util.List;

public interface PropertyDao {
	
    public List<Property> getAll();
    public List<Property> getFavourites(Long userId);
    public void setFavourite(Long userId, Long propertyId);
    public void deleteFavourite(Long userId, Long propertyId);

        public Property findById(final long id);
    
    public Long createProperty(String street, Integer number, Integer floor, String apartment, PropertyType type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice);
    public Long createProperty(String street, Integer number, Integer floor, String apartment, String neighborhood,
                               OperationType operationType, PropertyType type, User publisherUser, Long price,
                               Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage,
                               Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery);
    public List<Property> getFiltered(String filters, ArrayList params);
}
