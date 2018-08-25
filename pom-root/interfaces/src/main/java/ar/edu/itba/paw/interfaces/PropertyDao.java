package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Property;

import java.util.List;

public interface PropertyDao {
    public Property findById(final long id);
    public List<Property> getAll();
}
