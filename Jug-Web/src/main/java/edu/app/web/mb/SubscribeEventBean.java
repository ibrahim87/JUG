package edu.app.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.app.business.AttendedServiceLocal;
import edu.app.business.EventServiceLocal;
import edu.app.persistence.Attended;
import edu.app.persistence.Event;



@ManagedBean(name="attendedBean")
@SessionScoped
public class SubscribeEventBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Attended attended=new Attended();
	
	
	@EJB
	private AttendedServiceLocal attendedServiceLocal;
	
	@EJB
	EventServiceLocal eventServiceLocal;
	private Event event; 
	private List<Event>events=new ArrayList<Event>() ;
	private String firstname ;
	private String lastname;
	private String entreprise;
	private String mail ; 
	private String sexe;
	private String category;
	
	@PostConstruct
	public void init(){
		
		
		
	}
	
	
	
	public String SubscribeForEvent(){
		String navigateTo = null;
		
		
		event =eventServiceLocal.findlastevent();
		System.out.println("event="+event);
		events.add(event);
		attended.setEvents(events);
		attendedServiceLocal.createAttended(attended);
		
	
		FacesMessage message = new FacesMessage("Success! ! ");
		FacesContext.getCurrentInstance().addMessage(null, message);

		navigateTo = "/index?faces-redirect=true";
		
		return navigateTo;
	}



	public Attended getAttended() {
		return attended;
	}



	public void setAttended(Attended attended) {
		this.attended = attended;
	}



	public AttendedServiceLocal getAttendedServiceLocal() {
		return attendedServiceLocal;
	}



	public void setAttendedServiceLocal(AttendedServiceLocal attendedServiceLocal) {
		this.attendedServiceLocal = attendedServiceLocal;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEntreprise() {
		return entreprise;
	}



	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public String getSexe() {
		return sexe;
	}



	public void setSexe(String sexe) {
		this.sexe = sexe;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Event getEvent() {
		return event;
	}



	public void setEvent(Event event) {
		this.event = event;
	}



	public EventServiceLocal getEventServiceLocal() {
		return eventServiceLocal;
	}



	public void setEventServiceLocal(EventServiceLocal eventServiceLocal) {
		this.eventServiceLocal = eventServiceLocal;
	}



	public List<Event> getEvents() {
		return events;
	}



	public void setEvents(List<Event> events) {
		this.events = events;
	}

	
	
	
}
