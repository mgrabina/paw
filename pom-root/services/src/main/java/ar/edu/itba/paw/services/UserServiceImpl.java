package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.models.Property;
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

	@Autowired
	private PropertyDao propertyDao;


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
		try {
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				User user = userDao.findByMail(authentication.getName()).get();
				return user;
			}
		}catch (Exception e){
			return null;
		}
		return null; // TODO: Use Optionals
	}

	public List<Property> getFavourites(Long userId) {
		return userDao.getFavourites(userId);
	}

	public void setFavourite(Long userId, Long propertyId) {
		userDao.setFavourite(userId, propertyId);
	}

	public void deleteFavourite(Long userId, Long propertyId) {
		userDao.deleteFavourite(userId, propertyId);
	}
}
