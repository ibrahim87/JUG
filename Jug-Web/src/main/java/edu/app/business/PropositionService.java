package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Event;
import edu.app.persistence.Proposition;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@Stateless
public class PropositionService implements PropositionServiceRemote,
		PropositionServiceLocal {

	@PersistenceContext(name = "PU")
	private EntityManager em;

	public PropositionService() {
	}

	public void createProposition(Proposition proposition) {
		em.persist(proposition);
	}

	public void updateProposition(Proposition proposition) {
		em.merge(proposition);
	}

	public void deleteProposition(Proposition proposition) {
		em.remove(em.merge(proposition));
	}

	public Proposition findPropositionById(int idProposition) {
		Proposition proposition = null;
		proposition = em.find(Proposition.class, idProposition);
		return proposition;
		
		
		
	}

	
	
	

	
	@SuppressWarnings("unchecked")
	public List<Proposition> findAllProposition() {
		Query query = em.createQuery("select e from Proposition e where e.etatPro like :state");
		query.setParameter("state", "attente");
		return query.getResultList();
		
		
	}

	@SuppressWarnings("unchecked")
	public List<Proposition> findProposition(String title) {
		Query query = em
				.createQuery("select pro from Proposition pro where c.title LIKE :title");
		query.setParameter("title", title);
		return query.getResultList();
	}

	public List<Proposition> findByKeywordProposition(String keyword) {

		List<Proposition> toFind = null;
		Query query = em
				.createQuery("select pro from Proposition pro where UPPER(pro.title) like:param");
		query.setParameter("param", "%" + keyword.toUpperCase() + "%");
		toFind = query.getResultList();
		return toFind;

	}


	public List<Proposition> findPropositionBySpeaker(User user) {
		Query query = em
				.createQuery("select p from Proposition p where p.speaker.idUser=:user");
		query.setParameter("user", user.getidUser());
		return query.getResultList();
	
	}

	@Override
	public List<Proposition> findAllPropositionAccepted() {
		Query query = em
				.createQuery("select p from Proposition p where p.etatPro like :state");
				query.setParameter("state", "Accepter");
				
				return query.getResultList();
		
	}

	@Override
	public List<Proposition> findAllPropositionNotAccepted() {
		Query query = em
				.createQuery("select p from Proposition p where p.etatPro like :state");
				query.setParameter("state", "refused");
				
				return query.getResultList();
		
	}

}
