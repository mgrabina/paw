package ar.edu.itba.paw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public User GetUser() {
		// TODO Auto-generated method stub
		return userDao.GetUser();
	}

	public User findByMail(String mail) {
		return userDao.findByMail(mail).get();
	}
	public void createUser(String username, String surname ,String mail,String password, String phone){
		userDao.createUser(username,surname,mail,password,phone);
	}

	public List<User> getAll(){
		return userDao.getAll();
	}
	public User findById(final long id){
		return userDao.findById(id).get();
	}
	public User findByMail(final long mail){
		return userDao.findByMail(mail);
	}

}
