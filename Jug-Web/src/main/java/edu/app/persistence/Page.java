package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="t_page")
public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPage;
	private String titre ;
	private String contenu ;

	private Article article;
	
	public Page() {

	}
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	
	
	@ManyToOne
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	
	

	

}
