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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


import edu.app.business.ArticleServiceLocal;
import edu.app.business.GestionCategorieLocal;
import edu.app.business.SessionProvider;
import edu.app.persistence.Article;
import edu.app.persistence.Categorie;
import edu.app.persistence.Leader;
import edu.app.persistence.Picture;
import edu.app.persistence.User;

@ManagedBean(name = "articleBean")
@SessionScoped
public class ArticleBean implements Serializable {

	private User user;

	@ManagedProperty("#{SP}")
	private SessionProvider sessionProvider;

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
	private String secondContent;
	private List<Article> arts;
	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private List<Article> filtredArticles;
	private int selectedTypeStatus = -1;
	private String selcet;

	private List<SelectItem> filterOptions;
	private List<SelectItem> selectItemsForCategories;

	// private String categotie;
	// private String[] categoties={"java","jEE","Android"};
	//

	private Categorie categorie = new Categorie();
	private String contenu;

	private List<Article> arts2;
	private User uLeader = new Leader();

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

		// arts=articleServiceLocal.findAllArticleCustum(0, 2);
		// arts2=articleServiceLocal.findArticleByJUGLeader(uLeader);

	}
	
	 public void postProcessXLS(Object document) {
	        HSSFWorkbook wb = (HSSFWorkbook) document;
	        HSSFSheet sheet = wb.getSheetAt(0);
	        CellStyle style = wb.createCellStyle();
	        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

	        for (Row row : sheet) {
	            for (Cell cell : row) {
	                cell.setCellValue(cell.getStringCellValue().toUpperCase());
	                cell.setCellStyle(style);
	            }
	        }
	    }
	

	public void secondSaveListener() {
		secondContent = secondContent.replaceAll("\\r|\\n", "");

		final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Second Content",
				secondContent.length() > 150 ? secondContent.substring(0, 100)
						: secondContent);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveListener() {
		contenu = contenu.replaceAll("\\r|\\n", "");

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Content",
				contenu.length() > 2000 ? contenu.substring(0, 10000) : contenu);

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String doNew() {

		String navigateTo = null;

		user = sessionProvider.getConnectedUser();
		// pictures.add(picture);

		if (selectedTypeStatus == 1) {

			newaArticle.setStatus("private");

		}

		if (selectedTypeStatus == 2) {
			newaArticle.setStatus("public");

		}
		categorie = gestionCategorieLocal.findCategorieById(selectedCategoryId);
		newaArticle.setPicture(picture);

		newaArticle.setCategorie(categorie);
		categorie.getArticles().add(newaArticle);
		newaArticle.setUser(user);

		System.out.println("--------------------" + user);
		gestionCategorieLocal.updateCategorie(categorie);

		newaArticle = new Article();

		FacesMessage msg = new FacesMessage(
				"Success! , Your inscription is Done ");
		FacesContext.getCurrentInstance().addMessage(null, msg);

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

	public String update() {
		String navigateTo = null;
		articleServiceLocal.updateArticle(article);
		FacesMessage msg = new FacesMessage("Update! , Update done successfuly");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		navigateTo = "/pages/JUGMember/Home?faces-redirect=true";
		return navigateTo;

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
		return "/pages/JUGMember/AllArticles?faces-redirect=true";
	}

	

	public String AddArticle() {

		return "/pages/JUGMember/AddArticle?faces-redirect=true";
	}

	
	
	public String AllArticlesJUG() {
		return "/pages/JUGLeader/AllArticles?faces-redirect=true";
	}

	

	public String AddArticleJUG() {

		return "/pages/JUGLeader/AddArticle?faces-redirect=true";
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

		// DefaultStreamedContent streamedPic = new DefaultStreamedContent(
		// new ByteArrayInputStream(pictur.getContent()),
		// "image/png");
		//

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

	public void setSessionProvider(SessionProvider sessionProvider) {
		this.sessionProvider = sessionProvider;
	}

	public SessionProvider getSessionProvider() {
		return sessionProvider;
	}

	public List<Article> getArts() {
		return arts;
	}

	public void setArts(List<Article> arts) {
		this.arts = arts;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getSecondContent() {
		return secondContent;
	}

	public void setSecondContent(String secondContent) {
		this.secondContent = secondContent;
	}

	public List<Article> getArts2() {
		return arts2;
	}

	public void setArts2(List<Article> arts2) {
		this.arts2 = arts2;
	}

	public String getSelcet() {
		return selcet;
	}

	public void setSelcet(String selcet) {
		this.selcet = selcet;
	}

	// public String getCategotie() {
	// return categotie;
	// }
	//
	// public void setCategotie(String categotie) {
	// this.categotie = categotie;
	// }
	//
	// public String[] getCategoties() {
	// return categoties;
	// }
	//
	// public void setCategoties(String[] categoties) {
	// this.categoties = categoties;
	// }

}
