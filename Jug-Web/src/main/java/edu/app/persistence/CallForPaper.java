package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity(name="tcallforpaper") 
public class CallForPaper  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCfp;
	private String name ;
	private String description;
	private Date startDatecall;
	private Date endDatecall;
	private Proposition proposition;
	private Picture picture ;
	private Event event;
	
	public CallForPaper() {
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdCfp() {
		return idCfp;
	}

	public void setIdCfp(int idCfp) {
		this.idCfp = idCfp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDatecall() {
		return startDatecall;
	}

	public void setStartDatecall(Date startDatecall) {
		this.startDatecall = startDatecall;
	}

	public Date getEndDatecall() {
		return endDatecall;
	}

	public void setEndDatecall(Date endDatecall) {
		this.endDatecall = endDatecall;
	}
	
	@ManyToOne
	public Proposition getProposition() {
		return proposition;
	}
	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "picture_fk", unique = true)
	public Picture getPicture() {
		return picture;
	}


	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	@OneToOne
	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	
	
	
	
	
}
