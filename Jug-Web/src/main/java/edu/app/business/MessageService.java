package edu.app.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Message;
import edu.app.persistence.User;



/**
 * Session Bean implementation class MessageService
 */
@Stateless
public class MessageService implements MessageServiceRemote,
		MessageServiceLocal {

	/** The em. */
	@PersistenceContext(name = "PU")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MessageService() {
	}

	public void sendMessage(User sender, User receiver, String msg,
			boolean etat, Date date,String subject,Date updateDate) {
		Message message = new Message(date, msg, etat, sender, receiver, subject,updateDate);
		em.persist(message);

	}

	@SuppressWarnings("unchecked")
	public List<Message> findMessagebySender(User sender) {
		List<Message> toFind = null;

		Query query = em
				.createQuery("SELECT  m FROM Message m  WHERE m.sender=:z");

		query.setParameter("z", sender);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<Message> findMessagebyReceiver(User receiver) {
		List<Message> toFind = null;

		Query query = em
				.createQuery("SELECT  m FROM Message m  WHERE m.receiver=:z");

		query.setParameter("z", receiver);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<Message> findMessagebyName(String msg) {
		List<Message> toFind = null;

		Query query = em
				.createQuery("SELECT  m FROM Message m  WHERE m.message=:z");

		query.setParameter("z", msg);
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<Message> findAssignsBySenderAndReceiver(User sender,
			User receiver) {
		List<Message> toFind = null;

		Query query = em
				.createQuery("SELECT  m FROM Message m  WHERE m.sender=:s AND m.receiver =:r");

		query.setParameter("s", sender);
		query.setParameter("r", receiver);
		toFind = query.getResultList();
		return toFind;
	}

	public void updateMessage(Message message) {
		em.merge(message);
	}
 
	public void removeMessage(Message message) {
		em.remove(em.merge(message));
	}
	@SuppressWarnings("unchecked")
	public List<Message> findAssignsByReceiverAndStatus(User receiver,
			boolean etat) {
		List<Message> toFind = null;

		Query query = em
				.createQuery("SELECT  m FROM Message m  WHERE m.receiver=:r AND m.etat =:e order by m.messagePK.date desc");

		query.setParameter("r", receiver);
		query.setParameter("e",etat );
		toFind = query.getResultList();
		return toFind;
	}
	@SuppressWarnings("unchecked")
	public List<Message> findAssignsBySenderAndStatus(User sender,
			boolean etat) {
		List<Message> toFind = null;

		Query query = em
				.createQuery("SELECT  m FROM Message m  WHERE m.sender=:s AND m.etat =:e order by m.messagePK.date desc");

		query.setParameter("s", sender);
		query.setParameter("e",etat );
		toFind = query.getResultList();
		return toFind;
	}
}
