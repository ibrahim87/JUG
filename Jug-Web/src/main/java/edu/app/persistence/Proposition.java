package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="t_proposition")
public class Proposition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProp;
	private String title;
	private String organisation;
	private String propositionSpeaker;
	private String personnalProfile;
	private String companyProfile;
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

	public String getPersonnalProfile() {
		return personnalProfile;
	}

	public void setPersonnalProfile(String personnalProfile) {
		this.personnalProfile = personnalProfile;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyProfile == null) ? 0 : companyProfile.hashCode());
		result = prime * result + idProp;
		result = prime * result
				+ ((organisation == null) ? 0 : organisation.hashCode());
		result = prime
				* result
				+ ((personnalProfile == null) ? 0 : personnalProfile.hashCode());
		result = prime
				* result
				+ ((propositionSpeaker == null) ? 0 : propositionSpeaker
						.hashCode());
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
		if (companyProfile == null) {
			if (other.companyProfile != null)
				return false;
		} else if (!companyProfile.equals(other.companyProfile))
			return false;
		if (idProp != other.idProp)
			return false;
		if (organisation == null) {
			if (other.organisation != null)
				return false;
		} else if (!organisation.equals(other.organisation))
			return false;
		if (personnalProfile == null) {
			if (other.personnalProfile != null)
				return false;
		} else if (!personnalProfile.equals(other.personnalProfile))
			return false;
		if (propositionSpeaker == null) {
			if (other.propositionSpeaker != null)
				return false;
		} else if (!propositionSpeaker.equals(other.propositionSpeaker))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proposition [idProp=" + idProp + ", organisation="
				+ organisation + ", propositionSpeaker=" + propositionSpeaker
				+ ", personnalProfile=" + personnalProfile
				+ ", companyProfile=" + companyProfile + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne
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
	
	
}
