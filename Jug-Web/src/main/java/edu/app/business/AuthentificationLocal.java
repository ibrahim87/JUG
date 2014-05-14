package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.User;

/**
 * The Interface AuthentificationLocal.
 */
@Local
public interface AuthentificationLocal {

	public User authenticate(String login, String password);
	void createUser(User user);
	List<User> findAllUsers();
	
	boolean loginExists(String login);
	
	
}
