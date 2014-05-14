package edu.app.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="t_page")
public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPage;
	private String titre ;
	private String contenu ;
	private List<Picture>pictures;
	private Article article;
	
	public Page() {

	}
	
	@Id	
	public int getIdPage() {
		return idPage;
	}
	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContenu() {
		return contenu;
	}
	
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	@OneToMany(mappedBy = "page" ,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public List<Picture> getPictures() {
		if (pictures == null)
			pictures = new ArrayList<Picture>();
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	@ManyToOne
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	
	

	

}
