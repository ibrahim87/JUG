package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Role;


@Local
public interface RoleServiceLocal {

	void create(Role role);

	Role findById(int id);

	void update(Role role);

	
	void remove(Role role);

	List<Role> findAll();
	Role findByRoleName(String nom);

}
