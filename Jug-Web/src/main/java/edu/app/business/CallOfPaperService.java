package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.CallForPaper;
import edu.app.persistence.Event;

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
		return (List<CallForPaper>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public CallForPaper findCallForPaperBYName(String name) {
		Query query = em
				.createQuery("select c from CallForPaper c where c.titleCFP LIKE :name");
		query.setParameter("name", '%'+name+'%');
		return (CallForPaper) query.getSingleResult();
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

	@Override
	public CallForPaper findCallForPaperByEvent(Event event) {
	
		return null;
	}

//	@Override
//	public CallForPaper findCallforPaperByDate(int year) {
//	
//		Query query = em
//				.createQuery("select c from CallForPaper c where c.startDatecall LIKE :year");
//		query.setParameter("year", year);
//		return (CallForPaper) query.getSingleResult();
//	}

}
