package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;


import javax.sql.DataSource;
import java.io.*;
import java.net.URISyntaxException;

import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;


	private final static RowMapper<User> ROW_MAPPER = (rs, rowNum) -> User.CreateUser(
            rs.getInt("id"),
            rs.getString("name"),
			rs.getString("password"),
            rs.getString("phone"),
            rs.getString("mail"),
            rs.getString("imagesrc")
            );

	private final static PropertyMapper PROPERTY_MAPPER = new PropertyMapper();

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


	public Long createUser(String username, String password, String mail, String phone, String imageSrc) {

        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users").usingGeneratedKeyColumns("id");

        final Map<String, Object> args = new HashMap<String, Object>();
        args.put("name", username);
        args.put("password", password);
        args.put("phone", phone);
        args.put("mail", mail);
        args.put("imagesrc", imageSrc);
        
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
		final List<User> list = jdbcTemplate.query("SELECT * FROM users",
				ROW_MAPPER);
		if (list.isEmpty()) {
			return null;
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

	public void deleteFavourite(Long userId, Long propertyId){
		jdbcTemplate.update("DELETE FROM favourites WHERE id_user = ? and id_property = ?;", userId, propertyId);
	}

	public List<Property> getFavourites(Long userId){
		final List<Property> list = jdbcTemplate.query(
				"SELECT p.*, u.*, i.image_src FROM favourites " +
						" JOIN property p ON favourites.id_property = p.id " +
						" JOIN users u ON favourites.id_user = u.id " +
						" FULL OUTER JOIN property_images i on p.id = i.property_id "+
						" WHERE favourites.id_user = ? ;",
				PROPERTY_MAPPER, userId);
		if (list.isEmpty()) {
			return Collections.emptyList();
		}
		return list;
	}

}