package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Property;

import java.util.List;

public interface PropertyService {
    public Property findById(final long id);
    public List<Property> getAll();
    public Long createProperty(String street, Integer number, Integer floor, String apartment, Property.Type type, Long userId, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice);
    public Long createProperty(String street, Integer number, Integer floor, String apartment, Property.Type type, Long userId, Long price);
    public List<Property> getFavorites(Long userId);
}
