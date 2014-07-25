package edu.app.business;

import javax.ejb.Local;

import edu.app.persistence.Attended;

@Local
public interface AttendedServiceLocal {
	
	
	 void createAttended(Attended attended);

	 void updateAttended(Attended attended);

	 void deleteAttended(Attended attended);

}
