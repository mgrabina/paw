package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;

import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
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
			
	@Autowired
	public PropertyDaoImpl(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public Property findById(final long id) {
		final List<Property> list = jdbcTemplate.query("SELECT * FROM property WHERE id = ?",
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

	
	public Long createProperty(String street, Integer number, Integer floor, String apartment,
			PropertyType type, Long userId, Long price, Integer coveredArea,
							   Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice) {

		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("property").usingGeneratedKeyColumns("id");

		final Map<String, Object> args = new HashMap<String, Object>();
		args.put("street", street);
		args.put("number", number);
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

	@Override
	public Long createProperty(String street, Integer number, Integer floor, String apartment, String neighborhood, OperationType operationType, PropertyType type, User publisherUser, Long price, Integer coveredArea, Integer totalArea, Integer rooms, Integer baths, Boolean garage, Integer taxPrice, String adMessage, String adDescription, Boolean inmediateDelivery) {

		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("property").usingGeneratedKeyColumns("id");

		final Map<String, Object> args = new HashMap<String, Object>();
		args.put("street", street);
		args.put("number", number);
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

	public List<Property> getFavourites(Long userId){
		final List<Property> list = jdbcTemplate.query(
				"SELECT p.*, u.* FROM favourites " +
						" JOIN property p ON favourites.id_property = p.id " +
						" JOIN users u ON favourites.id_user = u.id" +
						" WHERE favourites.id_user = ? ;",
				ROW_MAPPER, userId);
		if (list.isEmpty()) {
			return Collections.emptyList();
		}
		return list;
	}

	public List<Property> getFiltered(String filters, ArrayList params){
		final List<Property> list = jdbcTemplate.query("SELECT * FROM property JOIN users ON property.user_id = users.id " +
															"FULL OUTER JOIN property_images i on property.id = i.property_id "+
															"WHERE "+ filters, params.toArray(), ROW_MAPPER);
		if (list.isEmpty()) {
			return Collections.emptyList();
		}
		return list;
	}

	public void setFavourite(Long userId, Long propertyId){
		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("favourites");
		final Map<String, Object> args = new HashMap<String, Object>();
		args.put("id_user", userId);
		args.put("id_property", propertyId);
		jdbcInsert.execute(args);
	}

}