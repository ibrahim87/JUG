package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Event;
import edu.app.persistence.User;
import edu.app.persistence.Vip;

@Local
public interface EventServiceLocal {

	void createEvent(Event event);

	void updateEvent(Event event);

	void deleteEvent(Event event);

	Event findEventById(int idEvent);

	List<Event> findAllEvent();

	List<Event> findAllEventNotDone();

	List<Event> findVipInvited(Vip vip);

	List<Event> findVipHaveNotEvent(Vip vip);

	List<Event> findEventSubscribed(Vip vip);

	Event StartHungOut(User JUGLeader);

	Event findVipHungOut(User vip);

}
