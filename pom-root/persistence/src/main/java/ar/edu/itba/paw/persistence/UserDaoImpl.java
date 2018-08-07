package ar.edu.itba.paw.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
	private JdbcTemplate jdbcTemplate;
	private final static RowMapper<User> ROW_MAPPER = new
			RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getString("username"), rs.getInt("userid"));
				}
			};
	@Autowired
	public UserDaoImpl(final DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public User findById(final long id) {
		final List<User> list = jdbcTemplate.query("SELEC T * FROM users WHERE userid = ?",
		ROW_MAPPER, id);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public User GetUser() {
		return new User("jorge",1);
	}
}