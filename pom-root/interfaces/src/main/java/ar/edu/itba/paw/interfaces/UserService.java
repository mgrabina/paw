package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

import java.util.List;

public interface UserService {

	public User GetUser();
	public User findByMail(String mail);
	public Long createUser(String username, String surname ,String mail,String password, String phone);
	public List<User> getAll();
	public User findById(final long id);
	public User findByMail(final long mail);
	public User getCurrentUser();
}
