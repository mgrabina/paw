package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PropertyDaoImpl implements PropertyDao {
	private JdbcTemplate jdbcTemplate;
	private final static RowMapper<Property> ROW_MAPPER = new
			RowMapper<Property>() {
				public Property mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new Property(
							rs.getInt("id"),
							rs.getString("street"),
							rs.getInt("number"),
							rs.getInt("floor"),
							rs.getString("apartment"),
							Property.Type.valueOf(rs.getString("type")),
							rs.getInt("user_id"),
							rs.getLong("price"),
							rs.getInt("covered_area"),
							rs.getInt("total_area"),
							rs.getInt("rooms"),
							rs.getInt("baths"),
							rs.getBoolean("garage"),
							rs.getInt("tax_price")
							);
				}
			};
	@Autowired
	public PropertyDaoImpl(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public Property findById(final long id) {
		final List<Property> list = jdbcTemplate.query("SELEC T * FROM property WHERE userid = ?",
		ROW_MAPPER, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<Property> getAll(){
		final List<Property> list = jdbcTemplate.query("SELEC T * FROM property",
				ROW_MAPPER);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
}