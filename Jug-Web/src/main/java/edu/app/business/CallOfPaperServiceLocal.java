package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Article;
import edu.app.persistence.CallForPaper;

@Local
public interface CallOfPaperServiceLocal {

	void createCallOfPaper(CallForPaper callForPaper);

	void updateCallOfPaper(CallForPaper callForPaper);

	void deleteCallOfPaper(CallForPaper callForPaper);

	CallForPaper findCallOfPaperById(int idCallOfPaper);

	List<CallForPaper> findAllCallForPaper();
	
	 List<CallForPaper> findOneCallForPaper(  int pageIndex, int noOfRecords);


	CallForPaper findCallForPaperBYName(String name);

	List<CallForPaper> findByKeywordCallForPaper(String keyword);
	
	//CallForPaper findCallforPaperByDate(int year);

}
