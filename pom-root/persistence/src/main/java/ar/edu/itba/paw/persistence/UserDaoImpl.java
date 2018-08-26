package ar.edu.itba.paw.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;


import javax.sql.DataSource;
import java.io.*;
import java.net.URISyntaxException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;


	private final static RowMapper<User> ROW_MAPPER = (rs, rowNum) -> new User(
            rs.getInt("id"),
            rs.getString("name"),
			rs.getString("surname"),
            rs.getString("password"),
            rs.getString("phone"),
            rs.getString("mail")
            );
	@Autowired
	public UserDaoImpl(final DataSource ds) throws IOException, URISyntaxException {

		jdbcTemplate = new JdbcTemplate(ds);
	}

	public Optional<User> findById(final long id){

		final List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE id = ?" , ROW_MAPPER, id);

		if (userList.isEmpty())
			return Optional.empty();
		else
			return Optional.of(userList.get(0));

	}

	public User GetUser() {
		return null;
	}

	public Optional<User> findByMail(final String mail){
		final List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE mail = ?" , ROW_MAPPER, mail);
		if (userList.isEmpty()){
			return Optional.empty();
		}
		else
			return Optional.of(userList.get(0));

	}

	public boolean userExist(final String mail){
		final List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE mail = ?" , ROW_MAPPER, mail);
		if (userList.isEmpty()){
			return false;
		}
		else
			return true;
	}

	public Optional<User> authFindByName(final String name){

		final List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE name = ?" , ROW_MAPPER, name);
		if (userList.isEmpty())
			return Optional.empty();
		else
			return Optional.of(userList.get(0));

	}


	public Optional<User> findByName(final String name){

		final List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE name = ?" , ROW_MAPPER, name);

		if (userList.isEmpty())
			return Optional.empty();
		else
			return Optional.of(userList.get(0));

	}


	public Long createUser(String username, String surname ,String mail,String password, String phone) {

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users").usingGeneratedKeyColumns("id");

        final Map<String, Object> args = new HashMap<String, Object>();
        args.put("name", username);
        args.put("surname", surname);
        args.put("password", password);
        args.put("phone", phone);
        args.put("mail", mail);
        final long userId = jdbcInsert.executeAndReturnKey(args).longValue();

        return userId;
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