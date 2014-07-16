package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Event;
import edu.app.persistence.User;


@Remote
public interface EventServiceRemote {

	void createEvent(Event event);

	void updateEvent(Event event);

	void deleteEvent(Event event);

	Event findEventById(int idEvent);

	List<Event> findAllEvent();

	List<Event> findAllEventNotDone();

	List<Event> findVipInvited(User user);

	List<Event> findVipHaveNotEvent(User user);

	List<Event> findEventSubscribed(User user);

	Event StartHungOut(User JUGLeader);

	Event findVipHungOut(User vip);
	
	Event findEventByTitle(String title);
	
}
