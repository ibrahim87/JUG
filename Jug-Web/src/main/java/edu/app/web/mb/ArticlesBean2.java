package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.ArticleServiceLocal;
import edu.app.business.PictureServiceLocal;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Article;
import edu.app.persistence.Leader;
import edu.app.persistence.Member;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@ManagedBean
@javax.faces.bean.RequestScoped
@SessionScoped
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
	UserServiceLocal userServiceLocal;
	
	private List<Article> arts;
	private List<Article> articleCategorie;
	private List<Speaker> speakers;
	private List<Member> members;
	private Article article = new Article();
	private List<Article> articles;
	private List<Leader> leaders;
	private int clickId;
	private User user = new User();
	private List<Article> artilesCategorie;
	private List<Article> ArtileFormCategorie = new ArrayList<Article>();
	private boolean displayDetails = false;
	private String etat;
	private String status;
	private String name;
	private List<Article> articlesJEE;
	private List<Article> OneArticleJava;
	private List<Article> OneArticleOther;
	private List<Article> allArtileOther;
	private String searchMovie;
	private List<Article> AllArticle;

	private DataModel dataModel = new ListDataModel();

	// private User user = new Leader();

	// private Leader =new Leader();

	public String getSearchMovie() {
		return searchMovie;
	}

	public void setSearchMovie(String searchMovie) {
		this.searchMovie = searchMovie;
	}

	@PostConstruct
	public void init() {

		picture = pictureServiceLocal.findPictureById(31).getContent();

		if (picture != null)
			streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
					picture));

		dataModel = getDataModel();

		arts = articleServiceLocal.findAllArticleCustum(status, 0, 4);
		

		speakers = userServiceLocal.findAllSpeakers();

	
		articleCategorie = articleServiceLocal.findArticleJava(0, 4);

		articles = articleServiceLocal.findArticleByJUGLeader(0, 4);

		members = userServiceLocal.findAllMembers(etat);
		leaders = userServiceLocal.findAllLeaders();

		articlesJEE = articleServiceLocal.findArticleByJEE(0, 4);

		OneArticleJava = articleServiceLocal.findOneArticleJava(0, 1);
		OneArticleOther = articleServiceLocal.findOneOtherArticle(0, 1);
		allArtileOther = articleServiceLocal.findArticleOther(0, 4);

	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void cancel() {
		article = new Article();
		displayDetails = false;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String modifDetail(String contenu) {
		String tempDetail = null;
		try {

			tempDetail = contenu.substring(0, 40);
			tempDetail = tempDetail + " ...";

		} catch (Exception e) {
		}
		return tempDetail;
	}

	public String modifDetailTitle(String title) {
		String tempDetail = null;
		try {

			tempDetail = title.substring(0, 30);
			tempDetail = tempDetail + " ...";

		} catch (Exception e) {
		}
		return tempDetail;
	}

	

	
	
	public String modifDetailContenu(String contenu) {
		String tempDetail = null;
		try {

			tempDetail = contenu.substring(0, 150);
			tempDetail = tempDetail + " ...";

		} catch (Exception e) {
		}
		return tempDetail;
	}

	public String voirDetailArticle() {
		article = (Article) dataModel.getRowData();

		return "";
	}

	public String updateArticle() {
		article = (Article) dataModel.getRowData();
		article.setStatus("public");
		articleServiceLocal.updateArticle(article);

		return "";
	}

	public String deleteArticle() {
		article = (Article) dataModel.getRowData();

		articleServiceLocal.deleteArticle(article);
		return "";
	}

	// public List<Article> findArticleBycathegorieName(Categorie categorie, int
	// pageIndex, int noOfRecords){
	//
	// arts2=findArticleBycathegorieName(categorie , 0 ,4);
	// List<Article> temp = new ArrayList<Article>() ;
	//
	// for (Article artic :arts2){
	// if(artic.getCategorie().getName().equals(categorie.getName()))
	// {
	// temp.add(artic);
	// }
	//
	//
	// }
	//
	// return temp;
	// }

	public StreamedContent getStreamedPic() {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context
				.getExternalContext().getRequest();

		Object o = myRequest.getParameter("photo");
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

	public String doDetail() {

		String navigateTo = null;

		String test = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("clickId");
		clickId = Integer.parseInt(test);
		article = articleServiceLocal.findArticleById(clickId);

		navigateTo = "/pages/Articledetail";
		return navigateTo;
	}

	public String doDetailUSER() {

		String navigateTo = null;

		String test = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("clickId");
		clickId = Integer.parseInt(test);

		user = userServiceLocal.findUserById(clickId);

		navigateTo = "/pages/Userdetail";
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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Leader> getLeaders() {
		return leaders;
	}

	public void setLeaders(List<Leader> leaders) {
		this.leaders = leaders;
	}

	public List<Article> getArticleCategorie() {
		return articleCategorie;
	}

	public void setArticleCategorie(List<Article> articleCategorie) {
		this.articleCategorie = articleCategorie;
	}

	public List<Article> getArtilesCategorie() {
		return artilesCategorie;
	}

	public void setArtilesCategorie(List<Article> artilesCategorie) {
		this.artilesCategorie = artilesCategorie;
	}

	public List<Article> getArticlesJEE() {
		return articlesJEE;
	}

	public void setArticlesJEE(List<Article> articlesJEE) {
		this.articlesJEE = articlesJEE;
	}

	public List<Article> getOneArticleJava() {
		return OneArticleJava;
	}

	public void setOneArticleJava(List<Article> oneArticleJava) {
		OneArticleJava = oneArticleJava;
	}

	public List<Article> getOneArticleOther() {
		return OneArticleOther;
	}

	public void setOneArticleOther(List<Article> oneArticleOther) {
		OneArticleOther = oneArticleOther;
	}

	public List<Article> getAllArtileOther() {
		return allArtileOther;
	}

	public void setAllArtileOther(List<Article> allArtileOther) {
		this.allArtileOther = allArtileOther;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Article> getAllArticle() {
		return AllArticle;
	}

	public void setAllArticle(List<Article> allArticle) {
		AllArticle = allArticle;
	}

	public List<Article> getArtileFormCategorie() {
		return ArtileFormCategorie;
	}

	public void setArtileFormCategorie(List<Article> artileFormCategorie) {
		ArtileFormCategorie = artileFormCategorie;
	}

	public DataModel getDataModel() {
		dataModel.setWrappedData(articleServiceLocal.findAllArticle());
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public boolean isDisplayDetails() {
		return displayDetails;
	}

	public void setDisplayDetails(boolean displayDetails) {
		this.displayDetails = displayDetails;
	}

}
