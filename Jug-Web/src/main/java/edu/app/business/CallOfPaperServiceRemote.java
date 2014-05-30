package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.CallForPaper;

@Remote
public interface CallOfPaperServiceRemote {

	void createCallOfPaper(CallForPaper callForPaper);

	void updateCallOfPaper(CallForPaper callForPaper);

	void deleteCallOfPaper(CallForPaper callForPaper);

	CallForPaper findCallOfPaperById(int idCallOfPaper);

	List<CallForPaper> findAllCallForPaper();

	List<CallForPaper> findCallForPaper(String name);

	List<CallForPaper> findByKeywordCallForPaper(String keyword);

}
