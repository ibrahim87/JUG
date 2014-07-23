package edu.app.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
public class Speaker extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Proposition> propositions;
	
	
	
	
	public Speaker() {
		
		super();
		
	}
	 @OneToMany(mappedBy = "speaker", cascade = CascadeType.PERSIST)
	 @JsonIgnore
	public List<Proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	}
	
	
	
}
