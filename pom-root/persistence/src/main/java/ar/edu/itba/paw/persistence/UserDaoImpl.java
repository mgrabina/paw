package ar.edu.itba.paw.persistence;

import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Override
	public User GetUser() {

		return new User("Jorge");
	}
	

}
