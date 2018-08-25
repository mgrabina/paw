package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

public interface UserService {
	
	public User GetUser();
	public User findByMail(String mail);
	public void createUser(String username, String surname ,String mail,String password, String phone);
}
