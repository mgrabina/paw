package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

public interface UserDao {

	public User findById(final long id);
	public User GetUser();
}
