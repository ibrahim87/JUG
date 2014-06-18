package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="t_callforpaper") 
public class CallForPaper  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCfp;
	private String TitleCFP ;
	private String description;
	private Date startDatecall;
	private Date endDatecall;

	private Picture picture ;
	private Event event;
	private List<Proposition>propositions;
	
	
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

	@OneToMany(fetch=FetchType.EAGER)
	public List<Proposition> getPropositions() {
		return propositions;
	}


	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	}


	public String getTitleCFP() {
		return TitleCFP;
	}


	public void setTitleCFP(String titleCFP) {
		TitleCFP = titleCFP;
	}
	
	
	
	
	
	
	
}
