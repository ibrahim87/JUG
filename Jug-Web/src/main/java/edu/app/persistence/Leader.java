package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class Leader  extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private List<Article>articles;
	
	public Leader() {
		
		super();
	}
	
	
	
	
//	@OneToMany(mappedBy="leader")
//	public List<Article> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(List<Article> articles) {
//		this.articles = articles;
//	}
//	
	
	
	
	
	
}
