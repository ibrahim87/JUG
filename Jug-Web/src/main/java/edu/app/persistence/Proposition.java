package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Column;
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
	private Speaker speaker;
	private CallForPaper callForPaper;
	private String EtatPro ;
	@Column(length=50000)
	private String detail ;
	private String duration;
	
	public Proposition() {
	}

	
	
	
	

	public Proposition(int idProp, String title, Speaker speaker,
			CallForPaper callForPaper, String etatPro, String detail,
			String duration) {
		super();
		this.idProp = idProp;
		this.title = title;
		this.speaker = speaker;
		this.callForPaper = callForPaper;
		EtatPro = etatPro;
		this.detail = detail;
		this.duration = duration;
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


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	@Override
	public String toString() {
		return "Proposition [idProp=" + idProp + ", title=" + title + "]";
	}


	
}
