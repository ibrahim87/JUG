package edu.app.business;

import javax.ejb.Remote;

import edu.app.persistence.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface AuthentificationRemote.
 */
@Remote
public interface AuthentificationRemote {
	
	/**
	 * Authenticate.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user
	 */
	public User authenticate(String login, String password);

}
