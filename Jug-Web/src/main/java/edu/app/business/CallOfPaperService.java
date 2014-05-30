package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.CallForPaper;

/**
 * Session Bean implementation class CallOfPaperService
 */
@Stateless
public class CallOfPaperService implements CallOfPaperServiceRemote,
		CallOfPaperServiceLocal {

	@PersistenceContext(name = "PU")
	private EntityManager em;

	public CallOfPaperService() {
	}

	public void createCallOfPaper(CallForPaper callForPaper) {
		em.persist(callForPaper);
	}

	public void updateCallOfPaper(CallForPaper callForPaper) {
		em.merge(callForPaper);
	}

	public void deleteCallOfPaper(CallForPaper callForPaper) {
		em.remove(em.merge(callForPaper));
	}

	public CallForPaper findCallOfPaperById(int idCallOfPaper) {
		CallForPaper callForPaper = null;
		callForPaper = em.find(CallForPaper.class, idCallOfPaper);
		return callForPaper;
	}

	@SuppressWarnings("unchecked")
	public List<CallForPaper> findAllCallForPaper() {
		Query query = em.createQuery("select c from CallForPaper c");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CallForPaper> findCallForPaper(String name) {
		Query query = em
				.createQuery("select c from CallForPaper c where c.name LIKE :name");
		query.setParameter("name", name);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CallForPaper> findByKeywordCallForPaper(String keyword) {
		List<CallForPaper> toFind = null;
		Query query = em
				.createQuery("select c from CallForPaper c where UPPER(c.name) like:param");
		query.setParameter("param", "%" + keyword.toUpperCase() + "%");
		toFind = query.getResultList();
		return toFind;
	}

}
