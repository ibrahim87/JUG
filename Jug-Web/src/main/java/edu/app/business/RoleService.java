package edu.app.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.app.persistence.Role;

/**
 * . Session Bean implementation class RoleService
 */
/**
 * @author user
 *
 */
@Stateless
@LocalBean
public class RoleService implements RoleServiceRemote, RoleServiceLocal {

	/**
	 * 
	 */
	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public RoleService() {
	}

	
	public void create(Role role) {
		em.persist(role);

	}

	public Role find(int id) {
		return em.find(Role.class, id);
	}

	/**
	 * update.
	 * @param role.
	 */
	public void update(Role role) {
		em.merge(role);
	}

	public void remove(Role role) {
		em.remove(em.merge(role));

	}

	public Role findById(int id) {
		return em.find(Role.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		return em.createQuery("select r from Role r").getResultList();

	}


	@Override
	public Role findByRoleName(String nom) {
		return (Role) em.createQuery("select m from Role m where m.name=:z")
				.setParameter("z", nom).getSingleResult();
	}

	
}
