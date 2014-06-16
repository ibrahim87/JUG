package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Message;
import edu.app.persistence.MessageAnswer;



/**
 * Session Bean implementation class MessageAnswerAnswerService
 */
@Stateless
public class MessageAnswerService implements MessageAnswerServiceRemote,
		MessageAnswerServiceLocal {

	@PersistenceContext(name = "PU")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MessageAnswerService() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<MessageAnswer> findMessageAnswerbyMessage(Message message) {
		List<MessageAnswer> toFind = null;

		Query query = em
				.createQuery("SELECT  ma FROM MessageAnswer ma  WHERE ma.message=:z");

		query.setParameter("z", message);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<MessageAnswer> findMessageAnswerbyName(String msg) {
		List<MessageAnswer> toFind = null;

		Query query = em
				.createQuery("SELECT  ma FROM MessageAnswer ma  WHERE m.answer=:z");

		query.setParameter("z", msg);
		toFind = query.getResultList();
		return toFind;
	}

	public void createMessageAnswer(MessageAnswer messageAnswer) {
		em.persist(messageAnswer);
	}

	public void updateMessageAnswer(MessageAnswer messageAnswer) {
		em.merge(messageAnswer);
	}

	public void removeMessageAnswer(MessageAnswer messageAnswer) {
		em.remove(em.merge(messageAnswer));
	}


}
