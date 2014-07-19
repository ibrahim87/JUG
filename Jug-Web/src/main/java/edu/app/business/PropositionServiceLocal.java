package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Proposition;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@Local
public interface PropositionServiceLocal {
	
	void createProposition(Proposition proposition);

	void updateProposition(Proposition proposition);

	void deleteProposition(Proposition proposition);

	Proposition findPropositionById(int idProposition);

	List<Proposition> findAllProposition();
	
	List<Proposition> findAllPropositionAccepted();
	List<Proposition> findAllPropositionNotAccepted();
	
	List<Proposition> findProposition(String name);

	List<Proposition> findByKeywordProposition(String keyword);
	
	List<Proposition> findPropositionBySpeaker(User user);
}
