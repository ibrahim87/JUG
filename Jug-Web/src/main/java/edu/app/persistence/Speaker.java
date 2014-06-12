package edu.app.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class Speaker extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Proposition> propositions;
	private String nationality;
	private String job;
	private String company;
	
	
	
	public Speaker() {
		
		super();
		
	}
	 @OneToMany(mappedBy = "speaker", cascade = CascadeType.PERSIST)
	public List<Proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	
}
