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
					return new User(
							rs.getInt("userid"),
							rs.getString("name"),
							rs.getString("username"),
							rs.getString("password"),
							rs.getString("phone"),
							rs.getString("mail"),
							rs.getString("imageSrc")
							);
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

	public User findByMail(final long mail) {
		final List<User> list = jdbcTemplate.query("SELEC T * FROM users WHERE mail = ?",
				ROW_MAPPER, mail);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<User> getAll(){
		final List<User> list = jdbcTemplate.query("SELEC T * FROM users",
				ROW_MAPPER);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
}