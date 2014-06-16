package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="t_proposition")
public class Proposition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProp;
	private String title;
	private String organisation;
	private String propositionSpeaker;
	private Date dateProposition;
	private Speaker speaker;
	private CallForPaper callForPaper;
	private boolean accepted ;
	
	public Proposition() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdProp() {
		return idProp;
	}

	public void setIdProp(int idProp) {
		this.idProp = idProp;
	}

	
	
	@ManyToOne
	@JoinColumn(name="speaker_fk")
	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	
	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getPropositionSpeaker() {
		return propositionSpeaker;
	}

	public void setPropositionSpeaker(String propositionSpeaker) {
		this.propositionSpeaker = propositionSpeaker;
	}

	

	

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProp;
		result = prime * result
				+ ((organisation == null) ? 0 : organisation.hashCode());
		result = prime
				* result
				+ ((propositionSpeaker == null) ? 0 : propositionSpeaker
						.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposition other = (Proposition) obj;
		if (idProp != other.idProp)
			return false;
		if (organisation == null) {
			if (other.organisation != null)
				return false;
		} else if (!organisation.equals(other.organisation))
			return false;
		if (propositionSpeaker == null) {
			if (other.propositionSpeaker != null)
				return false;
		} else if (!propositionSpeaker.equals(other.propositionSpeaker))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Proposition [idProp=" + idProp + ", title=" + title
				+ ", organisation=" + organisation + "]";
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne
	@JoinColumn(name="callforpaper_fk")
	public CallForPaper getCallForPaper() {
		return callForPaper;
	}


	public void setCallForPaper(CallForPaper callForPaper) {
		this.callForPaper = callForPaper;
	}


	public boolean isAccepted() {
		return accepted;
	}


	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}


	public Date getDateProposition() {
		return dateProposition;
	}


	public void setDateProposition(Date dateProposition) {
		this.dateProposition = dateProposition;
	}
	
	
}
