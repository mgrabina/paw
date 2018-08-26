package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

	public Optional<User> findById(final long id);

	public User GetUser();

	public Optional<User> findByMail(final String mail);

	public boolean userExist(final String mail);

	public Optional<User> authFindByName(final String name);

	public Optional<User> findByName(final String name);

	public Long createUser(String username, String surname, String mail, String password, String phone);


	public User findByMail(final long mail);

	public List<User> getAll();
}
