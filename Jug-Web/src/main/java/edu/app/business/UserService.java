package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Article;
import edu.app.persistence.Leader;
import edu.app.persistence.Member;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
public class UserService implements UserServiceRemote, UserServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext(name = "PU")
	private EntityManager em;
	
    public UserService() {
    	
    	
    }

	public void createUser(User user) {
		em.merge(user);
	}

	public void updateUser(User user) {
		em.merge(user);
	}

	public void removeUser(User user) {
		em.remove(findByUserLogin(user.getLogin()));
	}

	public List<User> findAllUser() {
		return (List<User>)em.createQuery("select u from User u ").getResultList();
	}
	@Override
	public List<Speaker> findAllSpeakers() {
		return (List<Speaker>)em.createQuery("select u from Speaker u ").getResultList();
	}

	public List<User> findByKeywordUser(String keyword) {
		
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param");
		query.setParameter("param", "%" + keyword.toUpperCase() + "%");
		toFind = query.getResultList();
		return toFind;
		
		
		
	}

	public User findByUserLogin(String nom) {
		User use = null;

		Query query = em.createQuery("select u from User u where u.login=:z")
				.setParameter("z", nom);
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

	public List<User> findByKeywordLogin(String login) {
		
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.login) like:param");
		query.setParameter("param", "%" + login.toUpperCase() + "%");
		
		toFind = query.getResultList();
		return toFind;
	}

	public List<User> findByKeywordLastname(String lastName) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param");
		query.setParameter("param", "%" + lastName.toUpperCase() + "%");
	
		toFind = query.getResultList();
		return toFind;
	}

	
	public List<User> findByStatus(String status) {
		List<User> toFind = null;

		Query query = em.createQuery("SELECT  u FROM User u  WHERE u.etat= :z");

		query.setParameter("z", status);
		toFind = query.getResultList();
		return toFind;
	}

	public List<User> findByParameter(String param) {
		List<User> toFind = null;
		Query query = em.createQuery("SELECT  u FROM User u  WHERE u.login=:p");
		query.setParameter("p", param);
		toFind = query.getResultList();
		return toFind;
	}

	
	public List<User> findByKeywordLastNameAndLogin(String lastName,
			String login) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param and UPPER(u.login) like:param1");
		query.setParameter("param", "%" + lastName.toUpperCase() + "%");
		query.setParameter("param1", "%" + login.toUpperCase() + "%");
		
		toFind = query.getResultList();
		return toFind;
		
	}


	public User findByUserMail(String name) {
		
		User use = null;

		Query query = em.createQuery("select u from User u where u.mail=:z")
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

	
	

	
	public void removeManyUser(List<User> users) {
		for (User user : users)
			em.remove(em.merge(user));
	}

	@Override
	public List<User> findByKeywordLastNameAndStatus(String lastName,
			String status) {
		List<User> toFind = null;
		Query query = em
				.createQuery("select u from User u where UPPER(u.nom) like:param and UPPER(u.etat) like:param1");
		query.setParameter("param", "%" + lastName.toUpperCase() + "%");
		query.setParameter("param1", "%" + status.toUpperCase() + "%");
		
		toFind = query.getResultList();
		return toFind;
	}

	@Override
	public boolean usernameExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from User u where u.login=:login";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		exists = (Boolean)query.getSingleResult();
		return exists;
	}

//	public User findUserById(int id) {
//		return em.find(User.class, id);
//		
//		
//		
//	}

	
	public List<Member> findAllMembers(String etat) {
		Query query = em.createQuery("select u from Member u where u.etat='Accepter'");
		return query.getResultList();
	}

	@Override
	public List<Leader> findAllLeaders() {
		return (List<Leader>)em.createQuery("select u from Leader u ").getResultList();
	}

	@Override
	public List<Member> findAllMembersJUG() {
		Query query = em
				.createQuery("select u from Member u where u.etat like :state");
				query.setParameter("state", "attente");
				
				return query.getResultList();
	}

	@Override
	public User findUserById(int idUser) {
		User user =null;
	
		user = em.find(User.class, idUser);
		return user;
		
	}

	@Override
	public void verification() {
	
		
	}
	

    
    
    

}
