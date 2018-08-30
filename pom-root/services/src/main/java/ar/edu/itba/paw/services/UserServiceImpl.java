package ar.edu.itba.paw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public Long createUser(String username, String password, String mail, String phone, String imageSrc){
		return userDao.createUser(username,password, mail, phone, imageSrc);
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

	public User getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			User user = userDao.findByMail(authentication.getName()).get();
			return user;
		}

		return null; // TODO: Use Optionals
	}
}
