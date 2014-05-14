package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.User;

/**
 * Session Bean implementation class Authentification
 */
@Stateless
public class Authentification implements AuthentificationRemote,
		AuthentificationLocal {

	/** The em. */
	@PersistenceContext(unitName = "PU")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public Authentification() {
	}

	public User authenticate(String login, String password) {

		System.out.println("-----------------------" + login);
		System.out.println("-----------------------" + password);
		User found = null;
		Query q = em
				.createQuery(
						"select u from User u where u.login=:p1 and u.password=:p2")
				.setParameter("p1", login).setParameter("p2", password);
		try {
			found = (User) q.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return found;

	}

	@Override
	public void createUser(User user) {
		em.persist(user);

	}

	@Override
	public List<User> findAllUsers() {
		return em.createQuery("select u from User u").getResultList();

	}

	@Override
	public boolean loginExists(String login) {

		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from User u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		exists = (Boolean) query.getSingleResult();
		return exists;
	}
}
