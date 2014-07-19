package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.CallForPaper;
import edu.app.persistence.Event;

@Remote
public interface CallOfPaperServiceRemote {

	void createCallOfPaper(CallForPaper callForPaper);

	void updateCallOfPaper(CallForPaper callForPaper);

	void deleteCallOfPaper(CallForPaper callForPaper);

	CallForPaper findCallOfPaperById(int idCallOfPaper);

	List<CallForPaper> findAllCallForPaper();

	CallForPaper findCallForPaperBYName(String name);

	List<CallForPaper> findByKeywordCallForPaper(String keyword);

	 CallForPaper findCallForPaperByEvent(Event event);
	 List<CallForPaper> findOneCallForPaper(  int pageIndex, int noOfRecords);

	
}
