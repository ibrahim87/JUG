package edu.app.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity(name="t_article")
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idArticle ;
	private String name ;
	private String status ;
	private String contenu ;
	private Date datecration ; 
	private List<Commentaire>commentaires;
	private List<Page>pages;
	
	
	@Id	
	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getDatecration() {
		return datecration;
	}

	public void setDatecration(Date datecration) {
		this.datecration = datecration;
	}
	@OneToMany(mappedBy = "article" ,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public List<Page> getPages() {
		if (pages == null)
			pages = new ArrayList<Page>();
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	@OneToMany(mappedBy = "article",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		if (commentaires == null)
			commentaires = new ArrayList<Commentaire>();
		this.commentaires = commentaires;
	}

	
	
	
	
	
}
