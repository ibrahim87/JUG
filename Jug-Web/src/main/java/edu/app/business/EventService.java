package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Event;
import edu.app.persistence.User;

/**
 * Session Bean implementation class EventService
 */
@Stateless
public class EventService implements EventServiceRemote, EventServiceLocal {

	@PersistenceContext(name = "PU")
	private EntityManager em;

	public EventService() {
	}

	public void createEvent(Event event) {
		em.persist(event);

	}

	public void updateEvent(Event event) {
		em.merge(event);
	}

	public void deleteEvent(Event event) {
		em.remove(em.merge(event));

	}

	public Event findEventById(int idEvent) {
		Event event = null;
		event = em.find(Event.class, idEvent);
		return event;
	}

	@SuppressWarnings("unchecked")
	public List<Event> findAllEvent() {
		Query query = em.createQuery("select e from Event e");
		return query.getResultList();
	}

	public List<Event> findAllEventNotDone() {
		Query query = em
				.createQuery("select e from Event e where e.endDate >= CURRENT_TIMESTAMP");
		return query.getResultList();
	}

//	public List<Event> findVipInvited(Vip vip) {
//		Query query = em
//				.createQuery("select e from InvitationEvent i join i.event e where i.vip=:vip and i.confirm=1 and  i.subscribe=0 ");
//		query.setParameter("vip", vip);
//		return query.getResultList();
//	}
//
//	public List<Event> findVipHaveNotEvent(Vip vip) {
//		Query query = em
//				.createQuery("select e from Vip v join v.invitationEvents i join i.event e where v  =:vip and i.confirm=0  and i.subscribe=0");
//		query.setParameter("vip", vip);
//		return query.getResultList();
//	}
//
//	public List<Event> findEventSubscribed(Vip vip) {
//		Query query = em
//				.createQuery("select e from Vip v join v.invitationEvents i join i.event e where v  =:vip and  i.subscribe=1");
//		query.setParameter("vip", vip);
//		return query.getResultList();
//	}

	public Event StartHungOut(User Leader) {
		Query query = em
				.createQuery("select e from Event e join e.movie m where m.cineaste.id=:cineaste and  e.startliveTalk <= CURRENT_TIMESTAMP");
		query.setParameter("cineaste", Leader.getidUser());
		Event event = null;
		event = (Event) query.getSingleResult();
		return event;
	}

	public Event findVipHungOut(User vip) {
		Event event = null;
		Query query = em
				.createQuery("select e from Vip v join v.invitationEvents i join i.event e where v  =:vip and  i.subscribe=1 and e.startliveTalk <= CURRENT_TIMESTAMP and e.endliveTalk >= CURRENT_TIMESTAMP ");
		query.setParameter("vip", vip);
		event = (Event) query.getSingleResult();
		return event;
	}

	@Override
	public List<Event> findVipInvited(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findVipHaveNotEvent(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findEventSubscribed(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event findEventByTitle(String title) {
		Query query = em.createQuery("select e from Event e where e.title=:t");
		query.setParameter("t", title);
		return  (Event) query.getSingleResult();
	}
	}


