package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.ArticleServiceLocal;
import edu.app.business.GestionCategorieLocal;
import edu.app.persistence.Article;
import edu.app.persistence.Categorie;
import edu.app.persistence.Member;
import edu.app.persistence.Picture;
import edu.app.persistence.User;

@ManagedBean(name = "articleBean")
@ViewScoped

public class ArticleBean implements Serializable {

	private User user = new Member();

	@EJB
	ArticleServiceLocal articleServiceLocal;

	@EJB
	GestionCategorieLocal gestionCategorieLocal;

	private List<Picture> pictures;
	private Picture picture = new Picture();
	private boolean formDisplayed = false;
	private int selectedCategoryId = -1;
	private static final long serialVersionUID = 1L;
	private Article newaArticle = new Article();
	private Article article = new Article();
	private boolean showFiledUpload = false;
	private List<Article> articles;
	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private List<Article> filtredArticles;
	private int selectedTypeStatus = -1;

	private List<SelectItem> filterOptions;
	private List<SelectItem> selectItemsForCategories;

	private Categorie categorie = new Categorie();

	public ArticleBean() {

	}

	@PostConstruct
	public void init() {

		articles = articleServiceLocal.findAllArticle();
		List<Categorie> categories = gestionCategorieLocal.findAllCategorie();

		selectItemsForCategories = new ArrayList<SelectItem>(
				categories.size() + 1);

		for (Categorie category : categories) {
			selectItemsForCategories.add(new SelectItem(category.getId(),
					category.getName()));
		}
		filterOptions = new ArrayList<SelectItem>(categories.size());
		filterOptions.add(new SelectItem("", "select"));
		for (Categorie category : categories) {
			filterOptions.add(new SelectItem(category.getName(), category
					.getName()));
		}
	}

	public String doNew() {

		String navigateTo = null;

		// pictures.add(picture);

		if (selectedTypeStatus == 1) {

			newaArticle.setStatus("private");

		}

		if (selectedTypeStatus == 2) {
			newaArticle.setStatus("public");

		}
		categorie = gestionCategorieLocal.findCategorieById(selectedCategoryId);
//		System.out.println("////////////////" + categorie.getName());
//		
//		System.out.println(getArticle().getCategorie() +  "  aaaa  ");
		newaArticle.setPicture(picture);
		//newaArticle.setPictures(pictures);

		newaArticle.setCategorie(categorie);
		categorie.getArticles().add(newaArticle);
		newaArticle.setUser(user);
		
		System.out.println("--------------------"+user);
		gestionCategorieLocal.updateCategorie(categorie);

		FacesMessage msg = new FacesMessage(
				"Success! , Your inscription is Done ");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		newaArticle = new Article();
		navigateTo = "/pages/JUGMember/Home";
		return navigateTo;
	}
	
	
	public void doNewArticle() {

		
		if (selectedTypeStatus == 1) {

			newaArticle.setStatus("private");

		}

		if (selectedTypeStatus == 2) {
			newaArticle.setStatus("public");

		}
		selectedCategoryId = -1;
		article = new Article();
	
		formDisplayed = true;

	}

	public void doCancel() {

		formDisplayed = false;
		article = new Article();
		articles = articleServiceLocal.findArticleByMember(user);

	}

	public void update() {

		articleServiceLocal.updateArticle(article);

		FacesMessage msg = new FacesMessage("Update! , Update done successfuly");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {

		formDisplayed = true;

		if (article.getUser() != null) {
			selectedCategoryId = article.getUser().getidUser();
		} else {
			selectedCategoryId = -1;
		}
	}

	public String AllArticles() {
		return "/pages/JUGMember/AllArticles";
	}

	public String EditArticle() {

		return "/page/JUGMember/EditArticle";
	}

	public String AddArticle() {

		return "/pages/JUGMember/AddArticle";
	}

	public String DeleteArticle() {

		return "/pages/JUGMember/DeleteArticle";

	}

	public void upload(FileUploadEvent event) throws IOException {

		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");

		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Do what you want with the file
		picture.setContent(event.getFile().getContents());
		picture.setPictureName(event.getFile().getFileName());
		user.setPicture(picture);
		streamedPicture = new DefaultStreamedContent(new ByteArrayInputStream(
				event.getFile().getContents()), "image/png");

		byte[] bytes = event.getFile().getContents();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		FileOutputStream out = new FileOutputStream(new File(destinationTemp
				+ event.getFile().getFileName()));
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);

	}

	public Article getNewaArticle() {
		return newaArticle;
	}

	public void setNewaArticle(Article newaArticle) {
		this.newaArticle = newaArticle;
	}

	public int getSelectedCategoryId() {
		return selectedCategoryId;
	}

	public void setSelectedCategoryId(int selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public boolean isShowFiledUpload() {
		return showFiledUpload;
	}

	public void setShowFiledUpload(boolean showFiledUpload) {
		this.showFiledUpload = showFiledUpload;
	}

	public String getDestinationTemp() {
		return destinationTemp;
	}

	public void setDestinationTemp(String destinationTemp) {
		this.destinationTemp = destinationTemp;
	}

	public DefaultStreamedContent getStreamedPicture() {
		return streamedPicture;
	}

	public void setStreamedPicture(DefaultStreamedContent streamedPicture) {
		this.streamedPicture = streamedPicture;
	}

	public StreamedContent getStreamedPic() {
		return streamedPic;
	}

	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Article> getFiltredArticles() {
		return filtredArticles;
	}

	public void setFiltredArticles(List<Article> filtredArticles) {
		this.filtredArticles = filtredArticles;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<SelectItem> getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(List<SelectItem> filterOptions) {
		this.filterOptions = filterOptions;
	}

	public List<SelectItem> getSelectItemsForCategories() {
		return selectItemsForCategories;
	}

	public void setSelectItemsForCategories(
			List<SelectItem> selectItemsForCategories) {
		this.selectItemsForCategories = selectItemsForCategories;
	}

	public int getSelectedTypeStatus() {
		return selectedTypeStatus;
	}

	public void setSelectedTypeStatus(int selectedTypeStatus) {
		this.selectedTypeStatus = selectedTypeStatus;
	}

	
	
}
