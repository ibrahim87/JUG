package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Role;


/**
 * The Interface RoleServiceRemote.
 */
@Remote
public interface RoleServiceRemote {

	/**
	 * Creates the.
	 *
	 * @param role the role
	 */
	void create(Role role);

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the role
	 */
	Role findById(int id);

	/**
	 * Update.
	 *
	 * @param role the role
	 */
	void update(Role role);

	/**
	 * Removes the.
	 *
	 * @param role the role
	 */
	void remove(Role role);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Role> findAll();

}
