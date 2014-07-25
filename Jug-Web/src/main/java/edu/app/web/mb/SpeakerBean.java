package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.PictureServiceLocal;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;
@ManagedBean
@SessionScoped
public class SpeakerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	UserServiceLocal userServiceLocal;
	@EJB
	PictureServiceLocal pictureServiceLocal;
	private StreamedContent streamedPic;
	private byte[] picture;
	private List<Speaker> speakers;
	private int clickId;
	private User user = new User();
	
	
	@PostConstruct
	public void init() {

		picture = pictureServiceLocal.findPictureById(31).getContent();

		if (picture != null)
			streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
					picture));
		
		speakers = userServiceLocal.findAllSpeakers();
		}

public SpeakerBean() {
	// TODO Auto-generated constructor stub
}

	public UserServiceLocal getUserServiceLocal() {
		return userServiceLocal;
	}



	public void setUserServiceLocal(UserServiceLocal userServiceLocal) {
		this.userServiceLocal = userServiceLocal;
	}



	public PictureServiceLocal getPictureServiceLocal() {
		return pictureServiceLocal;
	}



	public void setPictureServiceLocal(PictureServiceLocal pictureServiceLocal) {
		this.pictureServiceLocal = pictureServiceLocal;
	}



	public StreamedContent getStreamedPic() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Object o = myRequest.getParameter("photo");
		//System.out.println(o+"oooooooooooooooo");
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



	public byte[] getPicture() {
		return picture;
	}



	public void setPicture(byte[] picture) {
		this.picture = picture;
	}



	public List<Speaker> getSpeakers() {
		return speakers;
	}



	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}



	public int getClickId() {
		return clickId;
	}



	public void setClickId(int clickId) {
		this.clickId = clickId;
	}
	
	
	
	public String doDetailUSER() {

		String navigateTo = null;

		String test = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("clickId");
		System.out.println("testtttttttttt"+test);
		clickId = Integer.parseInt(test);

		setUser(userServiceLocal.findUserById(clickId));

		navigateTo = "/pages/Userdetail?faces-redirect=true";
		return navigateTo;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	
	
}
