package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.util.List;

public interface UserDao {

	public User findById(final long id);
	public User findByMail(final long mail);
	public List<User> getAll();
}
