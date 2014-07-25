package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.CallOfPaperServiceLocal;
import edu.app.business.EventServiceLocal;
import edu.app.business.PictureServiceLocal;
import edu.app.business.PropositionServiceLocal;
import edu.app.business.SessionProvider;
import edu.app.persistence.CallForPaper;
import edu.app.persistence.Event;
import edu.app.persistence.Picture;
import edu.app.persistence.Proposition;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@ManagedBean(name = "callforpaperBean")
@SessionScoped
public class CallForPaperBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private CallOfPaperServiceLocal callOfPaperServiceLocal;

	@EJB
	private PropositionServiceLocal propositionServiceLocal;
	
	@EJB
	private EventServiceLocal eventServiceLocal;

	@ManagedProperty("#{SP}")
	private SessionProvider sessionProvider;

	private User user;
	@EJB
	PictureServiceLocal pictureServiceLocal;
	
	private CallForPaper newcall = new CallForPaper();

	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private Picture picture = new Picture();
	private byte[] pictures;
	private List<Event> events;
	private List<String> selectedEvents;
	private List<Event> selectItemsForEvent;
	private List<Proposition>propositionsSpeaker;
	private Proposition proposition = new Proposition();
	private List<CallForPaper>callForPapers;
	private List<Event>eventsJUG;
	private Speaker speaker;
	private List<CallForPaper>callForPapersJUG;
	
	private CallForPaper callForPaper = new CallForPaper();
	
	private Event event = new Event();
	
	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@PostConstruct
	public void init() {
		
		
		eventsJUG=eventServiceLocal.findAllEventJUG();
		
		user = sessionProvider.getConnectedUser();
		pictures = pictureServiceLocal.findPictureById(31).getContent();

		if (picture != null)
			streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
					pictures));
		 
		callForPapers=callOfPaperServiceLocal.findOneCallForPaper(0, 1);
		
		callForPapersJUG=callOfPaperServiceLocal.findAllCallForPaper();
		
		propositionsSpeaker=propositionServiceLocal.findPropositionBySpeaker(user);
		
		
		List<Event> events = eventServiceLocal.findAllEvent(0, 8);
		
		
//		setSelectItemsForEvent(new ArrayList<Event>(
//				events.size()));

//		for (Event event : events) {
//			selectItemsForEvent.add(new Event(event.getId(),
//					event.getTitle()));
//		}
	}

	public CallForPaperBean() {

	}

	// Method
	public String AddNewCallForPaper() {

		String navigateTo = null;

		user = sessionProvider.getConnectedUser();

		newcall.setPicture(picture);
		System.out.println("eveeeeeeeeeeeeeeeeent"+event);
		
	System.out.println(selectedEvents.get(0));			
	    String title=selectedEvents.get(0);

		
		newcall.setEvent(eventServiceLocal.findEventByTitle(title));
		//eventServiceLocal.updateEvent(event);
		System.out.println("aaaaaaaaaaaaaaaaa"+event);
		callOfPaperServiceLocal.createCallOfPaper(newcall);

		FacesMessage messa = new FacesMessage("Success! ! ");
		FacesContext.getCurrentInstance().addMessage(null, messa);

		newcall = new CallForPaper();
		
		return navigateTo = "/pages/JUGLeader/Home?faces-redirect=true";

	}

	
	public String deleteCallForPaper() {
		user = sessionProvider.getConnectedUser();
	
		callOfPaperServiceLocal.deleteCallOfPaper(callForPaper);
		callForPapersJUG.remove(callForPaper);

		callForPaper= new CallForPaper();

		
		
		return null;
	}
	
	
	public String updateCallForPaper() {
		user = sessionProvider.getConnectedUser();
		
		callOfPaperServiceLocal.updateCallOfPaper(callForPaper);
		
		FacesMessage message = new FacesMessage("update  ! ");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;

	}
	
	public String SubmitProposition(){
		String navigateTo=null;
		Date end=callForPapers.get(0).getEndDatecall();
		Date current=new Date();
		if(current.after(end))
		{
			navigateTo="/pages/EndCallForPaper?faces-redirect=true";
		
		}
		else navigateTo="/pages/RegisterCallForPaper?faces-redirect=true";
		
		
		return navigateTo ;
	}
	
	public void upload(FileUploadEvent event) throws IOException {

		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");

		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Do what you want with the file
		picture.setContent(event.getFile().getContents());
		picture.setPictureName(event.getFile().getFileName());
		newcall.setPicture(picture);
		setStreamedPicture(new DefaultStreamedContent(new ByteArrayInputStream(
				event.getFile().getContents()), "image/png"));
///  application/pdf
		byte[] bytes = event.getFile().getContents();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		FileOutputStream out = new FileOutputStream(new File(destinationTemp
				+ event.getFile().getFileName()));
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);

	}

	// get && set
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SessionProvider getSessionProvider() {
		return sessionProvider;
	}

	public void setSessionProvider(SessionProvider sessionProvider) {
		this.sessionProvider = sessionProvider;
	}

	public CallForPaper getNewcall() {
		return newcall;
	}

	public void setNewcall(CallForPaper newcall) {
		this.newcall = newcall;
	}

	public CallOfPaperServiceLocal getCallOfPaperServiceLocal() {
		return callOfPaperServiceLocal;
	}

	public void setCallOfPaperServiceLocal(
			CallOfPaperServiceLocal callOfPaperServiceLocal) {
		this.callOfPaperServiceLocal = callOfPaperServiceLocal;
	}

	
	public StreamedContent getStreamedPic() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Object o = myRequest.getParameter("photo");
		if (o != null) {
			int imageID = Integer.parseInt(o.toString());
			streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
					pictureServiceLocal.findPictureById(imageID).getContent()));
		}

		return streamedPic;
	}
	

	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}

	public DefaultStreamedContent getStreamedPicture() {
		return streamedPicture;
	}

	public void setStreamedPicture(DefaultStreamedContent streamedPicture) {
		this.streamedPicture = streamedPicture;
	}

	public String getDestinationTemp() {
		return destinationTemp;
	}

	public void setDestinationTemp(String destinationTemp) {
		this.destinationTemp = destinationTemp;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public byte[] getPictures() {
		return pictures;
	}

	public void setPictures(byte[] pictures) {
		this.pictures = pictures;
	}

	public EventServiceLocal getEventServiceLocal() {
		return eventServiceLocal;
	}

	public void setEventServiceLocal(EventServiceLocal eventServiceLocal) {
		this.eventServiceLocal = eventServiceLocal;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	
	

	public List<String> getSelectedEvents() {
		return selectedEvents;
	}

	public void setSelectedEvents(List<String> selectedEvents) {
		this.selectedEvents = selectedEvents;
	}

	public List<Event> getSelectItemsForEvent() {
		return selectItemsForEvent;
	}

	public void setSelectItemsForEvent(List<Event> selectItemsForEvent) {
		this.selectItemsForEvent = selectItemsForEvent;
	}

	public List<CallForPaper> getCallForPapers() {
		return callForPapers;
	}

	public void setCallForPapers(List<CallForPaper> callForPapers) {
		this.callForPapers = callForPapers;
	}

	public PictureServiceLocal getPictureServiceLocal() {
		return pictureServiceLocal;
	}

	public void setPictureServiceLocal(PictureServiceLocal pictureServiceLocal) {
		this.pictureServiceLocal = pictureServiceLocal;
	}

	public List<Proposition> getPropositionsSpeaker() {
		return propositionsSpeaker;
	}

	public void setPropositionsSpeaker(List<Proposition> propositionsSpeaker) {
		this.propositionsSpeaker = propositionsSpeaker;
	}

	public PropositionServiceLocal getPropositionServiceLocal() {
		return propositionServiceLocal;
	}

	public void setPropositionServiceLocal(
			PropositionServiceLocal propositionServiceLocal) {
		this.propositionServiceLocal = propositionServiceLocal;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	public List<Event> getEventsJUG() {
		return eventsJUG;
	}

	public void setEventsJUG(List<Event> eventsJUG) {
		this.eventsJUG = eventsJUG;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<CallForPaper> getCallForPapersJUG() {
		return callForPapersJUG;
	}

	public void setCallForPapersJUG(List<CallForPaper> callForPapersJUG) {
		this.callForPapersJUG = callForPapersJUG;
	}

	public CallForPaper getCallForPaper() {
		return callForPaper;
	}

	public void setCallForPaper(CallForPaper callForPaper) {
		this.callForPaper = callForPaper;
	}

	
	

}
