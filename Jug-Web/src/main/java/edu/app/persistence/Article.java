package edu.app.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "t_article")
@XmlRootElement
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private int idArticle;
	private String title;
	@JsonIgnore
	private String status;
	@Column(length = 50000)
	private String contenu;
	private Date datecration;
	@JsonIgnore
	private User user;
	private String username;
	@JsonIgnore
	private List<Page> pages;
	@JsonIgnore
	private Picture picture;
	@JsonIgnore
	private String categorie;
	private String urlphotoArticle;
	
	public Article() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	@Temporal(TemporalType.DATE)
	public Date getDatecration() {
		return datecration;
	}

	public void setDatecration(Date datecration) {
		this.datecration = datecration;
	}

	@OneToMany(mappedBy = "article", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JsonIgnore
	public List<Page> getPages() {
		if (pages == null)
			pages = new ArrayList<Page>();
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_fk")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "picture_fk", unique = true)
	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return title;
	}

	

	public String getUsername() {
		if (user != null) {
			return user.getNom();	
		}
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getUrlphotoArticle() {
		return urlphotoArticle;
	}

	public void setUrlphotoArticle(String urlphotoArticle) {
		this.urlphotoArticle = urlphotoArticle;
	}

}
