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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.CallOfPaperServiceLocal;
import edu.app.business.EventServiceLocal;
import edu.app.business.SessionProvider;
import edu.app.persistence.CallForPaper;
import edu.app.persistence.Event;
import edu.app.persistence.Picture;
import edu.app.persistence.User;

@ManagedBean(name = "callforpaperBean")
@ViewScoped
public class CallForPaperBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private CallOfPaperServiceLocal callOfPaperServiceLocal;

	@EJB
	private EventServiceLocal eventServiceLocal;

	@ManagedProperty("#{SP}")
	private SessionProvider sessionProvider;

	private User user;

	private CallForPaper newcall = new CallForPaper();

	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private Picture picture = new Picture();
	private byte[] pictures;
	private List<Event> events;
	private List<String> selectedEvents;
	private List<SelectItem> selectItemsForEvent;
	@PostConstruct
	public void init() {
		
		
		List<Event> events = eventServiceLocal.findAllEvent();
		setSelectItemsForEvent(new ArrayList<SelectItem>(
				events.size() + 1));

		for (Event event : events) {
			selectItemsForEvent.add(new SelectItem(event.getId(),
					event.getTitle()));
		}
	}

	public CallForPaperBean() {

	}

	// Method
	public String AddNewCallForPaper() {

		String navigateTo = null;

		user = sessionProvider.getConnectedUser();

		newcall.setPicture(picture);
		callOfPaperServiceLocal.createCallOfPaper(newcall);

		FacesMessage messa = new FacesMessage("Success! ! ");
		FacesContext.getCurrentInstance().addMessage(null, messa);

		newcall = new CallForPaper();
		
		return navigateTo = "/pages/JUGLeader/Home?faces-redirect=true";

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

	public List<SelectItem> getSelectItemsForEvent() {
		return selectItemsForEvent;
	}

	public void setSelectItemsForEvent(List<SelectItem> selectItemsForEvent) {
		this.selectItemsForEvent = selectItemsForEvent;
	}

}
