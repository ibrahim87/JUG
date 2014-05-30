package edu.app.business;

import java.util.List;

import javax.ejb.Remote;


import edu.app.persistence.Proposition;

@Remote
public interface PropositionServiceRemote {

	void createProposition(Proposition proposition);

	void updateProposition(Proposition proposition);

	void deleteProposition(Proposition proposition);

	Proposition findPropositionById(int idProposition);

	List<Proposition> findAllProposition();

	List<Proposition> findProposition(String name);

	List<Proposition> findByKeywordProposition(String keyword);
}
