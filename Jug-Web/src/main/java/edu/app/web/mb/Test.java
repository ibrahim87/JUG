 package edu.app.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import edu.app.business.CallOfPaperServiceRemote;
import edu.app.business.EventServiceRemote;
import edu.app.business.PropositionServiceRemote;
import edu.app.business.UserServiceRemote;
import edu.app.persistence.CallForPaper;
import edu.app.persistence.Event;
import edu.app.persistence.Proposition;
import edu.app.persistence.Speaker;


@SessionScoped
@ManagedBean
public class Test implements Serializable {
	
	@EJB
	UserServiceRemote userServiceRemote;
	
	@EJB
	PropositionServiceRemote propositionServiceRemote;
	@EJB
	CallOfPaperServiceRemote callOfPaperServiceRemote;
	@EJB
	EventServiceRemote eventServiceRemote;
	private Event event;
	private CallForPaper callForPaper;
	//Speaker spk;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public String fetchSpeakersByEvent(String title)
//	{
//		//List<Speaker> speakers= new ArrayList<Speaker>();
//		List<Proposition> propositions= new ArrayList<Proposition>();
//		
//		  event=new Event(title);
//		  
//		  
//		  callForPaper=event.getCallForPaper();
//		  propositions=callForPaper.getPropositions();
//		  		  
//		return propositions.get(0).getSpeaker().getNom();
//		for(Speaker speaker:userServiceRemote.findAllSpeakers()){
//			
//			for(Proposition p:speaker.getPropositions()){
//				
//				for(CallForPaper cPaper:callOfPaperServiceRemote.findAllCallForPaper())
//				{
//					if(cPaper.equals(p.getCallForPaper())){
//						callForPapers.add(cPaper);
//					}
//				}
//				for(CallForPaper call:callForPapers)
//				{
//					if(call.getEvent().getTitle().equals(nameOfEvent))
//					{
//						speakers.add(speaker);
//					}
//				}
//			}
//		}
		
		
//	}
//	
//}
//		  
		  
		  
		  
}	  
		  
		  
		  
		  
		  
//		

//		
//	}
//	
//
//}
