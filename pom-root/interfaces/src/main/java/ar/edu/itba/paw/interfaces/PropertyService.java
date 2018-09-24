package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PropertyService {
    public Property findById(final long id);
    public List<Property> getAll();
    public List<Property> getAllByUserId(final long id);

    public Long createProperty(String street, Integer floor, String apartment, PropertyType type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice);
    public Long createProperty(String street, Integer floor, String apartment, PropertyType type, Long userId, Long price);
    public Long createProperty(String street, Integer floor, String apartment, String neighborhood,
                    OperationType operationType, PropertyType type, User publisherUser, Long price,
                    Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage,
                    Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery);
    public void linkImage(String imageSrc, long propertyId);
    public List<Property> getFiltered(Map<String,String> filters);

    public List<Property> getPropertiesByTagsSearch(String search);
    public List<Property> getPropertiesByTagsSearch(List<String> tags);
    public List<String> getAllTags();
    public Map<Integer, Map<Integer, String>> getPotentialFilters();
    public Optional<Property> getPropertyById(long id);
    public Boolean propertyExists(long id);
}
