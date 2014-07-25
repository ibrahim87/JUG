package edu.app.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.app.persistence.Attended;

/**
 * Session Bean implementation class AttendedService
 */
@Stateless
public class AttendedService implements AttendedServiceLocal {

	@PersistenceContext(name="PU")
	private EntityManager em;
	
    public AttendedService() {
        
    }
    
    
    public void createAttended(Attended attended) {
		em.persist(attended);
		
	}

	public void updateAttended(Attended attended) {
		em.merge(attended);
		
	}

	public void deleteAttended(Attended attended) {
		em.remove(em.merge(attended));
		
	}

}
