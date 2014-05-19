package edu.app.business;

import java.security.acl.Group;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Role;
import edu.app.persistence.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UserService implements UserServiceRemote, UserServiceLocal {

	@PersistenceContext(name = "PU")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserService() {
	}

	public void createUser(User user) {
		em.persist(user);
	}

	public void updateUser(User user) {
		em.merge(user);
	}

	public void removeUser(User user) {
		em.remove(findByUserLogin(user.getLogin()));
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUser() {
		return em.createQuery("select u from User u ").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> findByKeywordUser(String keyword) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param");
		query.setParameter("param", "%" + keyword.toUpperCase() + "%");
		toFind = query.getResultList();
		return toFind;
	}

	public User findByUserLogin(String name) {

		User use = null;

		Query query = em.createQuery("select u from User u where u.login=:z")
				.setParameter("z", name);
		Object obj = null;
		try {
			obj = query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		if (obj != null)
			use = (User) obj;

		return use;

	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByRole(Role role) {
		List<User> toFind = null;

		Query query = em.createQuery("SELECT  u FROM User u  WHERE u.role= :z");
		query.setParameter("z", role);
		toFind = query.getResultList();
		return toFind;

	}

	@SuppressWarnings("unchecked")
	public List<User> findByRoleAndState(Role role, String etat) {
		List<User> toFind = null;

		Query query = em
				.createQuery("SELECT  u FROM User u  WHERE u.role=:r AND u.etat =:e");

		query.setParameter("r", role);
		query.setParameter("e", etat);
		toFind = query.getResultList();
		return toFind;
	}

	public User findByLoginAndPass(String login, String password) {
		User user = null;

		Query query = em
				.createQuery("SELECT  u FROM User u  WHERE u.login=:l AND u.password =:p");
		query.setParameter("l", login);
		query.setParameter("p", password);
		try {
			user = (User) query.getSingleResult();
		} catch (Exception exp) {
			return null;
		}
		return user;
	}

	public void removeManyUser(List<User> users) {
		for (User user : users)
			em.remove(em.merge(user));
	}

	@SuppressWarnings("unchecked")
	public List<User> findByParameter(String param) {
		List<User> toFind = null;
		Query query = em.createQuery("SELECT  u FROM User u  WHERE u.login=:p");
		query.setParameter("p", param);
		toFind = query.getResultList();
		return toFind;
	}

	

	

	@SuppressWarnings("unchecked")
	public List<User> findByKeywordLastname(String lastName, Role role) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param and u.role=:r");
		query.setParameter("param", "%" + lastName.toUpperCase() + "%");
		query.setParameter("r", role);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByStatus(String status) {
		List<User> toFind = null;

		Query query = em.createQuery("SELECT  u FROM User u  WHERE u.etat= :z");

		query.setParameter("z", status);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByKeywordLastNameAndLogin(String lastName,
			String login, Role role) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param and UPPER(u.login) like:param1 and u.role=:r");
		query.setParameter("param", "%" + lastName.toUpperCase() + "%");
		query.setParameter("param1", "%" + login.toUpperCase() + "%");
		query.setParameter("r", role);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByKeywordLastNameAndStatus(String lastName,
			String status, Role role) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param and UPPER(u.etat) like:param1 and u.role=:r");
		query.setParameter("param", "%" + lastName.toUpperCase() + "%");
		query.setParameter("param1", "%" + status.toUpperCase() + "%");
		query.setParameter("r", role);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByKeywordAll(String lastName, String status,
			String login, Role role) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param and UPPER(u.etat) like:param1 and UPPER(u.login) like:param2 and u.role=:r");
		query.setParameter("param", "%" + lastName.toUpperCase() + "%");
		query.setParameter("param1", "%" + status.toUpperCase() + "%");
		query.setParameter("param2", "%" + login.toUpperCase() + "%");
		query.setParameter("r", role);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByKeywordLogin(String login, Role role) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.login) like:param and u.role=:r");
		query.setParameter("param", "%" + login.toUpperCase() + "%");
		query.setParameter("r", role);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByKeywordStatusAndLogin(String status, String login,
			Role role) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.login) like:param and UPPER(u.etat) like:param1 and u.role=:r");
		query.setParameter("param", "%" + login.toUpperCase() + "%");
		query.setParameter("param1", "%" + status.toUpperCase() + "%");
		query.setParameter("r", role);
		toFind = query.getResultList();
		return toFind;
	}

	public User findUserCreatorByGroup(Group group, String type) {
		User user = null;

		Query query = em
				.createQuery("select distinct u from User u join u.userGroupInformations ug where ug.group=:z and ug.userType=:t");
		query.setParameter("z", group);
		query.setParameter("t", type);
		Object obj = null;
		try {
			obj = query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		if (obj != null)
			user = (User) obj;

		return user;

	}

	@SuppressWarnings("unchecked")
	public List<User> findUserMembresOfGroup(Group group) {

		return em
				.createQuery(
						"select distinct u from User u join u.userGroupInformations ug where ug.group=:z")
				.setParameter("z", group).getResultList();

	}

	public User findUserById(int id) {
		return em.find(User.class, id);
	}
	
	public boolean usernameExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from User u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		exists = (Boolean)query.getSingleResult();
		return exists;
	}

	@Override
	public User findUserByEmail(String mail) {
		User use = null;

		Query query = em.createQuery("select u from User u where u.mail=:z")
				.setParameter("z", mail);
		Object obj = null;
		try {
			obj = query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		if (obj != null)
			use = (User) obj;

		return use;
	}

	

	
}
