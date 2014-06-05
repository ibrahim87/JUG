package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.PictureServiceLocal;

@ManagedBean
@javax.faces.bean.RequestScoped
public class ArticlesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StreamedContent streamedPic;
	private byte[] picture;

	@EJB
	PictureServiceLocal pictureServiceLocal;

	public StreamedContent getStreamedPic() {
		return streamedPic;
	}

	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}

	@ManagedProperty("#{param.photo}")
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@PostConstruct
	public void init() {
		
		picture = pictureServiceLocal.findPictureById(27).getContent();
		if (picture != null)
			streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
					picture));

	}
	
	
	
	

}
