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
	private Speaker speaker;
	private CallForPaper callForPaper;
	private String EtatPro ;
	
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


	public String getEtatPro() {
		return EtatPro;
	}


	public void setEtatPro(String etatPro) {
		EtatPro = etatPro;
	}


	
}
