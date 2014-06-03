package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity(name="t_picture")
public class Picture implements Serializable {

	private int idPicture;
	private String pictureName;
	private String path;
	private byte[] content;
	private User user;
	private CallForPaper callForPaper;
	private Event event ;
	private static final long serialVersionUID = 1L;
	private Article article ;
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getIdPicture() {
		return idPicture;
	}

	public void setIdPicture(int idPicture) {
		this.idPicture = idPicture;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Lob
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
 
	@OneToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	@OneToOne
	public CallForPaper getCallForPaper() {
		return callForPaper;
	}

	public void setCallForPaper(CallForPaper callForPaper) {
		this.callForPaper = callForPaper;
	}
	
	
	@ManyToOne
	@JoinColumn(name="event_fk")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	@OneToOne
	@JoinColumn(name="article_fk")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	

	
	
	
	
	

}
