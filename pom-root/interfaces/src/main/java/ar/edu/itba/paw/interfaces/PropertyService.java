package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.User;

import java.util.List;
import java.util.Map;

public interface PropertyService {
    public Property findById(final long id);
    public List<Property> getAll();
    public List<Property> getPage(final List<Property> propertiesList, final int pageNumber);
    public List<Property> getPage(final List<Property> propertiesList);
	public int getPageCount(List<Property> propertiesList);

    public Long createProperty(String street, Integer number, Integer floor, String apartment, PropertyType type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice);
    public Long createProperty(String street, Integer number, Integer floor, String apartment, PropertyType type, Long userId, Long price);
    public Long createProperty(String street, Integer number, Integer floor, String apartment, String neighborhood,
                    OperationType operationType, PropertyType type, User publisherUser, Long price,
                    Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage,
                    Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery);
    public List<Property> getFiltered(Map<String,String> filters);
    public List<Property> getFavourites(Long userId);
    public void setFavourite(Long userId, Long propertyId);

}
