package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Property> getFavorites(Long userId){
		final List<Property> list = jdbcTemplate.query(
				"SELECT * " +
						"FROM wish " +
						"INNER JOIN property WHERE wish.user_id = ?",
				ROW_MAPPER, userId);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
}