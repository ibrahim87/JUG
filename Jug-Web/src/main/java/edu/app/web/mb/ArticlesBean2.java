package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.ArticleServiceLocal;
import edu.app.business.PictureServiceLocal;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Article;
import edu.app.persistence.Speaker;

@ManagedBean
@javax.faces.bean.RequestScoped
public class ArticlesBean2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StreamedContent streamedPic;
	private byte[] picture;
	@EJB
	 ArticleServiceLocal articleServiceLocal;
	@EJB
	PictureServiceLocal pictureServiceLocal;
	
	@EJB
	UserServiceLocal userServiceLocal ;
	private List<Article> arts ;
	private List<Article>arts2;
	private List<Speaker> speakers;
	private Article article = new Article() ;
	private List<Article>articles;

	private int clickId;
	private String name;
	
	
	@PostConstruct
	public void init() {
		
		
	picture = pictureServiceLocal.findPictureById(31).getContent();
		
		if (picture != null)
			streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
					picture));
		
		
		
		
		arts=articleServiceLocal.findAllArticleCustum(0, 5);
		articles=articleServiceLocal.findArticleByNameCategorie(name, 0, 8);
		
		speakers=userServiceLocal.findAllSpeakers();
		

		
	}

	
	
	public String modifDetail(String contenu)
	{
		String tempDetail = null;
		try{
		
		
		tempDetail=contenu.substring(0,40);
		tempDetail=tempDetail+" ...";
		
		}catch(Exception e){}
		return tempDetail;
		}
	
	
	
	
	
	
//	 public List<Article> findArticleBycathegorieName(Categorie nameCategorie, int pageIndex, int noOfRecords){
//		 arts2=findArticleBycathegorieName(nameCategorie , 0 ,4);
//		 List<Article> temp = new ArrayList<Article>() ;
//		 
//			 for (Article artic :arts2){
//				 if(artic.getCategorie().getName().equals(nameCategorie.getName()))
//						 {
//					 temp.add(artic);
//						 }
//				 
//				 
//			 }
//			 
//		 return temp;
//	 }
//	
	
	public StreamedContent getStreamedPic() {
		
		FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        
        Object o=myRequest.getParameter("photo");
        if (o!=null){
        int imageID =Integer.parseInt(o.toString());
        streamedPic= new DefaultStreamedContent(new ByteArrayInputStream(pictureServiceLocal.findPictureById(imageID).getContent()));
        }
		
		
		return streamedPic;
	}
	
	
	

	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}



	public String doDetail() {
	
	String navigateTo = null;
		
		
	String test = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("clickId");
	clickId = Integer.parseInt(test);
	article =  articleServiceLocal.findArticleById(clickId);   
	
	
	
	navigateTo = "/pages/Articledetail";
	return navigateTo;
}
	
	
	
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}



	public List<Article> getArts() {
		
		System.out.println(arts.size());
		return arts;
	}

	public void setArts(List<Article> arts) {
		this.arts = arts;
	}




	public ArticleServiceLocal getArticleServiceLocal() {
		return articleServiceLocal;
	}




	public void setArticleServiceLocal(ArticleServiceLocal articleServiceLocal) {
		this.articleServiceLocal = articleServiceLocal;
	}




	public PictureServiceLocal getPictureServiceLocal() {
		return pictureServiceLocal;
	}




	public void setPictureServiceLocal(PictureServiceLocal pictureServiceLocal) {
		this.pictureServiceLocal = pictureServiceLocal;
	}




	public Article getArticle() {
		return article;
	}




	public void setArticle(Article article) {
		this.article = article;
	}


	public List<Article> getArts2() {
		return arts2;
	}


	public void setArts2(List<Article> arts2) {
		this.arts2 = arts2;
	}






	public List<Article> getArticles() {
		return articles;
	}






	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public List<Speaker> getSpeakers() {
		return speakers;
	}



	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}



	


	





	
	

}
