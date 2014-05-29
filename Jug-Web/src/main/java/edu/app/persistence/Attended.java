package edu.app.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_attended")
public class Attended  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idAttended;
	private List<Event>events;
	
	public Attended() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdAttended() {
		return idAttended;
	}


	public void setIdAttended(int idAttended) {
		this.idAttended = idAttended;
	}

	@ManyToMany
	public List<Event> getEvents() {
		return events;
	}


	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
