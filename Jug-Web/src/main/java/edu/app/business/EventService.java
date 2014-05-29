package edu.app.business;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class EventService
 */
@Stateless
public class EventService implements EventServiceRemote, EventServiceLocal {

    /**
     * Default constructor. 
     */
    public EventService() {
        // TODO Auto-generated constructor stub
    }

}
