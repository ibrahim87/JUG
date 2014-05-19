package edu.app.business;

import java.security.acl.Group;
import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Role;
import edu.app.persistence.User;

/**
 * The Interface UserServiceLocal.
 */
@Local
public interface UserServiceLocal {
	/**
	 * Creates the user.
	 * 
	 * @param user
	 *            the user
	 */
	void createUser(User user);

	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 */
	void updateUser(User user);

	/**
	 * Removes the user.
	 * 
	 * @param user
	 *            the user
	 */
	void removeUser(User user);

	/**
	 * Find all user.
	 * 
	 * @return the list
	 */
	List<User> findAllUser();

	/**
	 * Find by keyword user.
	 * 
	 * @param keyword
	 *            the keyword
	 * @return the list
	 */
	List<User> findByKeywordUser(String keyword);

	/**
	 * Find by user login.
	 * 
	 * @param nom
	 *            the nom
	 * @return the user
	 */
	User findByUserLogin(String nom);

	/**
	 * Find by role.
	 * 
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findByRole(Role role);

	/**
	 * Find by role and state.
	 * 
	 * @param role
	 *            the role
	 * @param etat
	 *            the etat
	 * @return the list
	 */
	public List<User> findByRoleAndState(Role role, String etat);

	/**
	 * Find by login and pass.
	 * 
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return the user
	 */
	public User findByLoginAndPass(String login, String password);

	/**
	 * Removes the many user.
	 * 
	 * @param users
	 *            the users
	 */
	public void removeManyUser(List<User> users);

	/**
	 * Find by parameter.
	 * 
	 * @param param
	 *            the param
	 * @return the list
	 */
	public List<User> findByParameter(String param);

	

	/**
	 * Find by keyword login.
	 * 
	 * @param login
	 *            the login
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findByKeywordLogin(String login, Role role);

	/**
	 * Find by keyword lastname.
	 * 
	 * @param lastName
	 *            the last name
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findByKeywordLastname(String lastName, Role role);

	/**
	 * Find by status.
	 * 
	 * @param status
	 *            the status
	 * @return the list
	 */
	public List<User> findByStatus(String status);

	/**
	 * Find by keyword last name and login.
	 * 
	 * @param lastName
	 *            the last name
	 * @param login
	 *            the login
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findByKeywordLastNameAndLogin(String lastName,
			String login, Role role);

	/**
	 * Find by keyword last name and status.
	 * 
	 * @param lastName
	 *            the last name
	 * @param status
	 *            the status
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findByKeywordLastNameAndStatus(String lastName,
			String status, Role role);

	/**
	 * Find by keyword all.
	 * 
	 * @param lastName
	 *            the last name
	 * @param status
	 *            the status
	 * @param login
	 *            the login
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findByKeywordAll(String lastName, String status,
			String login, Role role);

	/**
	 * Find by keyword status and login.
	 * 
	 * @param status
	 *            the status
	 * @param login
	 *            the login
	 * @param role
	 *            the role
	 * @return the list
	 */
	public List<User> findByKeywordStatusAndLogin(String status, String login,
			Role role);

	public List<User> findUserMembresOfGroup(Group group);

	public User findUserCreatorByGroup(Group group, String type);

	public User findUserById(int id);

	public boolean usernameExists(String login);
	
	User findUserByEmail(String mail);
}
