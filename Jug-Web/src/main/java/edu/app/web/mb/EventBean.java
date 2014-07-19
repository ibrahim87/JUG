package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

import edu.app.business.CallOfPaperServiceRemote;
import edu.app.business.EventServiceLocal;
import edu.app.business.PictureServiceLocal;
import edu.app.business.PropositionServiceLocal;
import edu.app.business.PropositionServiceRemote;
import edu.app.business.SessionProvider;
import edu.app.business.UserServiceRemote;
import edu.app.persistence.CallForPaper;
import edu.app.persistence.Event;
import edu.app.persistence.Picture;
import edu.app.persistence.Proposition;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@ManagedBean(name = "eventBean")
@SessionScoped
public class EventBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;

	@EJB
	private EventServiceLocal eventServiceLocal;

	@EJB
	private PictureServiceLocal pictureServiceLocal;

	@EJB
	UserServiceRemote userServiceRemote;

	@EJB
	PropositionServiceRemote propositionServiceRemote;
	@EJB
	PropositionServiceLocal propositionServiceLocal;
	@EJB
	CallOfPaperServiceRemote callOfPaperServiceRemote;
	private int clickId;

	@ManagedProperty("#{SP}")
	private SessionProvider sessionProvider;

	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private Picture picture = new Picture();
	private List<Picture> picutreNewEvent = new ArrayList<Picture>();
	private Event newEvent = new Event();
	private List<Event> events;
	private byte[] pictures;
	
	private List<StreamedContent> allStreamedPictures=new ArrayList<StreamedContent>();
	
	private Event event = new Event();
	
	

	@PostConstruct
	public void init() {

		pictures = pictureServiceLocal.findPictureById(31).getContent();

		if (picture != null)
			streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
					pictures));

		events = eventServiceLocal.findAllEvent(0, 4);

		//event=eventServiceLocal.findEventById(1);
		//List<Picture> allPictures=pictureServiceLocal.findAllPictureByEvent(event);
		
		
		
		List<Picture> allPictures=pictureServiceLocal.findAllPicture();
		
		
		//System.out.println("tout les pictures + "+allPictures);
		for(Picture picture:allPictures){
			
			allStreamedPictures.add(getStramFromPicture(picture));
			
			
			
		
		}
		
		
//		for (StreamedContent s:allStreamedPictures){
//			System.out.println(s.getStream());
//		}

		
		//System.out.println("allStreamedPictures++++++++++++"+allStreamedPictures);
	}

	public EventBean() {
	}

	
	
	
	public void upload(FileUploadEvent event) throws IOException {

		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");

		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Do what you want with the file
		picture.setContent(event.getFile().getContents());
		picture.setPictureName(event.getFile().getFileName());
		picutreNewEvent.add(picture);
		newEvent.addPictures(picutreNewEvent);
		setStreamedPicture(new DefaultStreamedContent(new ByteArrayInputStream(
				event.getFile().getContents()), "image/png"));

		byte[] bytes = event.getFile().getContents();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		FileOutputStream out = new FileOutputStream(new File(destinationTemp
				+ event.getFile().getFileName()));
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);

	}

	
	public void uploadLogo(FileUploadEvent event) throws IOException {

		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");

		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Do what you want with the file
		picture.setContent(event.getFile().getContents());
		picture.setPictureName(event.getFile().getFileName());
		
		
		//picutreNewEvent.add(picture);
		setStreamedPicture(new DefaultStreamedContent(new ByteArrayInputStream(
				event.getFile().getContents()), "image/png"));

		byte[] bytes = event.getFile().getContents();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		FileOutputStream out = new FileOutputStream(new File(destinationTemp
				+ event.getFile().getFileName()));
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);

	}
	
	public String deleteEvent() {
		user = sessionProvider.getConnectedUser();
		
		
	//	System.out.println("event00000000000 "+event);
		
		eventServiceLocal.deleteEvent(event);
	//	System.out.println("event1111111111111111 "+event);
		
		
		events.remove(event);
	//	System.out.println("event2222222222222222 "+event);
		
		event = new Event();
		//System.out.println("event3333333333333 "+event);
		
		
		return null;
	}
	
	

	public String update() {
		user = sessionProvider.getConnectedUser();
		System.out.println("uppppppppppp1111111 "+event);
		eventServiceLocal.updateEvent(event);
		System.out.println("uppppppppppp2 "+event);
		FacesMessage message = new FacesMessage("update  ! ");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;

	}
	
	public String modifDetailTitle(String title) {
		String tempDetail = null;
		try {

			tempDetail = title.substring(0, 30);
			tempDetail = tempDetail + " ...";

		} catch (Exception e) {
		}
		return tempDetail;
	}
	
	
	public String doDetailEvent() {

		String navigateTo = null;

		String test = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("clickId");
		clickId = Integer.parseInt(test);
		
		event=eventServiceLocal.findEventById(clickId);
		

		navigateTo = "/pages/Eventdetail";
		return navigateTo;
	}


	public String doNewEvent() {

		String navigateTo = null;

		user = sessionProvider.getConnectedUser();

		newEvent.setLogo(picture);

		eventServiceLocal.createEvent(newEvent);

		newEvent = new Event();

		FacesMessage message = new FacesMessage("Success! ! ");
		FacesContext.getCurrentInstance().addMessage(null, message);

		navigateTo = "/pages/JUGLeader/Home?faces-redirect=true";
		return navigateTo;
	}

	public DefaultStreamedContent getStreamedPicture() {
		return streamedPicture;
	}

	public void setStreamedPicture(DefaultStreamedContent streamedPicture) {
		this.streamedPicture = streamedPicture;
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
	
	
	public StreamedContent getStramFromPicture(Picture picture){
		
		return new DefaultStreamedContent(new ByteArrayInputStream(picture.getContent()));

	}

	
	
	//
	public List<Speaker> fetchSpeakersByEvent(String title) {
		// List<Speaker> speakers= new ArrayList<Speaker>();
	

		List<Speaker> listeSpeakers = new ArrayList<Speaker>();
		for (Speaker speaker : userServiceRemote.findAllSpeakers()) {

			for (Proposition p : speaker.getPropositions()) {
				List<CallForPaper> callForPapers = new ArrayList<CallForPaper>();
				for (CallForPaper cPaper : callOfPaperServiceRemote
						.findAllCallForPaper()) {
					if (cPaper
							.getTitleCFP()
							.toLowerCase()
							.equals(p.getCallForPaper().getTitleCFP()
									.toLowerCase())) {
						callForPapers.add(cPaper);
					}
				}
				for (CallForPaper call : callForPapers) {
					if (call.getEvent().getTitle().toLowerCase()
							.equals(title.toLowerCase())) {
						listeSpeakers.add(speaker);
					}
				}
			}
		}
		return listeSpeakers;
	}
	
	
	///les events el 7dhar fihoum speaker
	public List<Event> getEventBySpeaker(Speaker speaker){
		
		List<Proposition> propositions= new ArrayList<Proposition>();
		List<Event> events= new ArrayList<Event>();
		for(Proposition prop:propositionServiceLocal.findAllPropositionAccepted())
		{
			if(prop.getSpeaker().getLogin().equals(speaker.getLogin())){
				propositions.add(prop);
			}
		}
		for(CallForPaper paper:callOfPaperServiceRemote.findAllCallForPaper())
		{
			//paper.getEvent().getCallForPaper().getPropositions()
			if(!events.contains(paper.getEvent())){
				events.add(event);
			}
		}
		return null;
	}

	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}

	public Event getNewEvent() {
		return newEvent;
	}

	public void setNewEvent(Event newEvent) {
		this.newEvent = newEvent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EventServiceLocal getEventServiceLocal() {
		return eventServiceLocal;
	}

	public void setEventServiceLocal(EventServiceLocal eventServiceLocal) {
		this.eventServiceLocal = eventServiceLocal;
	}

	public PictureServiceLocal getPictureServiceLocal() {
		return pictureServiceLocal;
	}

	public void setPictureServiceLocal(PictureServiceLocal pictureServiceLocal) {
		this.pictureServiceLocal = pictureServiceLocal;
	}

	public SessionProvider getSessionProvider() {
		return sessionProvider;
	}

	public void setSessionProvider(SessionProvider sessionProvider) {
		this.sessionProvider = sessionProvider;
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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public byte[] getPictures() {
		return pictures;
	}

	public void setPictures(byte[] pictures) {
		this.pictures = pictures;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getClickId() {
		return clickId;
	}

	public void setClickId(int clickId) {
		this.clickId = clickId;
	}

	public List<StreamedContent> getAllStreamedPictures() {
		return allStreamedPictures;
	}

	public void setAllStreamedPictures(List<StreamedContent> allStreamedPictures) {
		this.allStreamedPictures = allStreamedPictures;
	}

	public List<Picture> getPicutreNewEvent() {
		return picutreNewEvent;
	}

	public void setPicutreNewEvent(List<Picture> picutreNewEvent) {
		this.picutreNewEvent = picutreNewEvent;
	}

	public UserServiceRemote getUserServiceRemote() {
		return userServiceRemote;
	}

	public void setUserServiceRemote(UserServiceRemote userServiceRemote) {
		this.userServiceRemote = userServiceRemote;
	}

	

	
}
