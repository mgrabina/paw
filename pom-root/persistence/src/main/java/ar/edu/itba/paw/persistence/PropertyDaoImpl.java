package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;

import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class PropertyDaoImpl implements PropertyDao {
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	private final static PropertyMapper ROW_MAPPER = new PropertyMapper();
	private final static FilterMapper FILTER_MAPPER = new FilterMapper();

	@Autowired
	public PropertyDaoImpl(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public Property findById(final long id) {
		final List<Property> list = jdbcTemplate.query("SELECT * FROM property JOIN users ON property.user_id = users.id FULL OUTER JOIN property_images i on property.id = i.property_id where property.id=?",
		ROW_MAPPER, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<Property> getAll(){
		final List<Property> list = jdbcTemplate.query("SELECT * FROM property JOIN users ON property.user_id = users.id FULL OUTER JOIN property_images i on property.id = i.property_id",
				ROW_MAPPER);
		
		if (list.isEmpty()) {
			return null;
		}
		
		return list;
	}

	public List<Property> getAllByUserId(final long id){
		//final List<Property> list = jdbcTemplate.query("SELECT * FROM property FULL OUTER JOIN property_images i on property.id = i.property_id where property.user_id = ?",
				//ROW_MAPPER, id);
        final List<Property> list = jdbcTemplate.query("SELECT * FROM property JOIN users ON property.user_id = users.id FULL OUTER JOIN property_images i on property.id = i.property_id where property.user_id = ?;",
                ROW_MAPPER, id);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	
	public Long createProperty(String street, Integer floor, String apartment,
			PropertyType type, Long userId, Long price, Integer coveredArea,
							   Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {

		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("property").usingGeneratedKeyColumns("id");

		final Map<String, Object> args = new HashMap<String, Object>();
		args.put("street", street);
		args.put("floor", floor);
		args.put("apartment", apartment);
		args.put("type", type);
		args.put("user_id", userId);
		args.put("price", price);
		args.put("covered_area", coveredArea);
		args.put("total_area", totalArea);
		args.put("rooms", rooms);
		args.put("baths", baths);
		args.put("garage", garage);
		args.put("tax_price", taxPrice);

		final long propertyId = jdbcInsert.executeAndReturnKey(args).longValue();

		return propertyId;
	}

	public Long createProperty(String street, Integer floor, String apartment, String neighborhood, OperationType operationType, PropertyType type, User publisherUser, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery) {

		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("property").usingGeneratedKeyColumns("id");

		final Map<String, Object> args = new HashMap<String, Object>();
		args.put("street", street);
		args.put("floor", floor);
		args.put("apartment", apartment);
		args.put("neighborhood", neighborhood.toLowerCase().trim());
		args.put("operation_type", operationType);
		args.put("type", type);
		args.put("user_id", publisherUser.getId());
		args.put("price", price);
		args.put("covered_area", coveredArea);
		args.put("total_area", totalArea);
		args.put("rooms", rooms);
		args.put("baths", baths);
		args.put("garage", garage);
		args.put("tax_price", taxPrice);
		args.put("ad_message", adMessage);
		args.put("ad_description", adDescription);
		args.put("ad_date", new Timestamp(System.currentTimeMillis()));
		args.put("inmediate_delivery", inmediateDelivery);

		final long propertyId = jdbcInsert.executeAndReturnKey(args).longValue();

		return propertyId;
	}

    public Long createProperty(String street, Integer floor, String apartment, String neighborhood, OperationType operationType, PropertyType type, User publisherUser, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery, Map<String, Integer> tags) {

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("property").usingGeneratedKeyColumns("id");

        final Map<String, Object> args = new HashMap<String, Object>();
        args.put("street", street);
        args.put("floor", floor);
        args.put("apartment", apartment);
        args.put("neighborhood", neighborhood.toLowerCase().trim());
        args.put("operation_type", operationType);
        args.put("type", type);
        args.put("user_id", publisherUser.getId());
        args.put("price", price);
        args.put("covered_area", coveredArea);
        args.put("total_area", totalArea);
        args.put("rooms", rooms);
        args.put("baths", baths);
        args.put("garage", garage);
        args.put("tax_price", taxPrice);
        args.put("ad_message", adMessage);
        args.put("ad_description", adDescription);
        args.put("ad_date", new Timestamp(System.currentTimeMillis()));
        args.put("inmediate_delivery", inmediateDelivery);

        final long propertyId = jdbcInsert.executeAndReturnKey(args).longValue();

        //Tags Insertion
        final Map<String, Object> auxMap = new HashMap<String, Object>();
		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("tags");
		auxMap.put("id_property", propertyId);
		tags.entrySet().stream().filter(entry -> entry.getKey() != null).forEach(tag -> {
			auxMap.put("name", tag.getKey());
			auxMap.put("type", tag.getValue());
			jdbcInsert.execute(auxMap);
		});
        return propertyId;
    }
    public Map<Integer, Map<String, Integer>> getPotentialFilters(){
        final Map<Integer, Map<String, Integer>> map = jdbcTemplate.query(
                "select type, name, count(name) as count " +
                        " from tags " +
                        " group by type, name " +
                        " order by type, count(name) desc; ",
                FILTER_MAPPER);
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        return map;
    }

	@Override
	public Optional<Property> getById(long id) {
		final List<Property> propertyList = jdbcTemplate.query(
				"SELECT * FROM property " +
				"JOIN users ON property.user_id = users.id " +
				"LEFT OUTER JOIN property_images i on property.id = i.property_id  " +
						"WHERE property.id = ?" , ROW_MAPPER, id);

		if (propertyList.isEmpty())
			return Optional.empty();
		else
			return Optional.of(propertyList.get(0));
	}


	
    public void addImage(String imageSrc, long propertyId) { 
    	
    	jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("property_images").usingGeneratedKeyColumns("id");

        final Map<String, Object> args = new HashMap<String, Object>();
        args.put("image_src", imageSrc);
        args.put("property_id", propertyId);
       
        jdbcInsert.execute(args);
    }

	public List<Property> getFiltered(String filters, ArrayList params, String order){
		List<Property> list=null;

		String sql="select * from (SELECT distinct on (property.id) * FROM property JOIN users ON property.user_id = users.id " +
					"left OUTER JOIN property_images i on property.id = i.property_id ";
		list = jdbcTemplate.query(sql+
									"WHERE " + filters +
									") as p ORDER BY " + order, params.toArray(), ROW_MAPPER);

		if (list.isEmpty()) {
			return Collections.emptyList();
		}

		return list;
	}



	public List<Property> getByTag(String tag){
		final List<Property> list = jdbcTemplate.query(
				"SELECT property.*, users.* FROM property " +
						"JOIN users ON property.user_id = users.id " +
						"JOIN tags ON property.id = tags.id_property",
				ROW_MAPPER);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	public List<Property> getByTags(List<String> tags){
		if(tags.isEmpty())return null;
		final List<Property> list = jdbcTemplate.query(
				"SELECT * FROM property " +
						"JOIN users ON property.user_id = users.id " +
						"FULL OUTER JOIN property_images i on property.id = i.property_id " +
						"where property.id in( " +
						"  select tags.id_property " +
						"  from tags " +
						"  where tags.name in ("+"'"+String.join("' , '", tags)+"'"+") " +
						"  group by tags.id_property " +
						"  having count(distinct tags.name) = "+tags.stream().distinct().count()+
						");",
				ROW_MAPPER);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	public List<String> getAllTags(){
		final List<String> list = jdbcTemplate.queryForList(
				"select distinct name from tags;", String.class);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	public void delete(final long id) {
		jdbcTemplate.update("DELETE FROM property WHERE id = ?;", id);
	}

	public void update(final long id, final String desc, final long price, final String message) {
		jdbcTemplate.update("UPDATE property SET ad_description = ?, price = ?, ad_message = ?, ad_date = ? WHERE id = ?;",
				desc, price, message, new Timestamp(System.currentTimeMillis()), id);
	}


}