package edu.app.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.AnotherEmailSenderRemote;
import edu.app.business.PictureServiceLocal;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Member;
import edu.app.persistence.Picture;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;


@ManagedBean(name = "creatBean")
@ViewScoped
@SessionScoped
public class CreateUserBean implements Serializable{

	@EJB
	private AnotherEmailSenderRemote anotherEmailSenderRemote;
	@EJB
	private UserServiceLocal userServiceLocal;

	@EJB
	private PictureServiceLocal pictureServiceLocal;
	
	
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private String userType = "";
	private Picture picture = new Picture();
	private int selectedTypeUser = -1;
	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private boolean skip;
	private boolean showFiledUpload = false;

	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private List<User> picUsers = new ArrayList<User>();
	private String password2;
	private String nom;
	private String prenom;
	private String etat;
	private String login;
	private String password;
	private String sexe;
	private Date dateNaiss;
	private String email2;
	private String mail;
	
	
	public String createuser(){
		String navigateTo = null;
		
		if (selectedTypeUser == 1) {

			user = new Member();

		}

		if (selectedTypeUser == 2) {

			user = new Speaker();

		}
		
		user.setEtat("attente");
		user.setPicture(picture);
		userServiceLocal.createUser(user);
		selectedTypeUser = -1;
		anotherEmailSenderRemote
				.sendMail(
						user.getMail(),
						"Register",
						" Hello Mr , and Mrs. felicitation you registered in our website, you have access to our site crossing   \n Your UserName is :=  "
								+ user.getLogin()
								+ "\n Your Password is :=    "
								+ user.getPassword());

		FacesMessage msg = new FacesMessage(
				"Success! , Your inscription is Done ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		user = new User();
		
		
		return navigateTo="/index";
		
	}
	
	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}





	public String getUserType() {
		return userType;
	}





	public void setUserType(String userType) {
		this.userType = userType;
	}





	public String getDestinationTemp() {
		return destinationTemp;
	}





	public void setDestinationTemp(String destinationTemp) {
		this.destinationTemp = destinationTemp;
	}





	public List<User> getPicUsers() {
		return picUsers;
	}





	public void setPicUsers(List<User> picUsers) {
		this.picUsers = picUsers;
	}





	public boolean isShowFiledUpload() {
		return showFiledUpload;
	}





	public void setShowFiledUpload(boolean showFiledUpload) {
		this.showFiledUpload = showFiledUpload;
	}





	public boolean isSkip() {
		return skip;
	}





	public void setSkip(boolean skip) {
		this.skip = skip;
	}





	public DefaultStreamedContent getStreamedPicture() {
		return streamedPicture;
	}





	public void setStreamedPicture(DefaultStreamedContent streamedPicture) {
		this.streamedPicture = streamedPicture;
	}





	public String getPassword2() {
		return password2;
	}





	public void setPassword2(String password2) {
		this.password2 = password2;
	}





	public String getEmail2() {
		return email2;
	}





	public void setEmail2(String email2) {
		this.email2 = email2;
	}





	public String getMail() {
		return mail;
	}





	public void setMail(String mail) {
		this.mail = mail;
	}





	public Picture getPicture() {
		return picture;
	}





	public void setPicture(Picture picture) {
		this.picture = picture;
	}





	public AnotherEmailSenderRemote getAnotherEmailSenderRemote() {
		return anotherEmailSenderRemote;
	}





	public void setAnotherEmailSenderRemote(
			AnotherEmailSenderRemote anotherEmailSenderRemote) {
		this.anotherEmailSenderRemote = anotherEmailSenderRemote;
	}





	public UserServiceLocal getUserServiceLocal() {
		return userServiceLocal;
	}





	public void setUserServiceLocal(UserServiceLocal userServiceLocal) {
		this.userServiceLocal = userServiceLocal;
	}





	public PictureServiceLocal getPictureServiceLocal() {
		return pictureServiceLocal;
	}





	public void setPictureServiceLocal(PictureServiceLocal pictureServiceLocal) {
		this.pictureServiceLocal = pictureServiceLocal;
	}





	public int getSelectedTypeUser() {
		return selectedTypeUser;
	}





	public void setSelectedTypeUser(int selectedTypeUser) {
		this.selectedTypeUser = selectedTypeUser;
	}





	public StreamedContent getStreamedPic() {
		return streamedPic;
	}





	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}





	public String getNom() {
		return nom;
	}





	public void setNom(String nom) {
		this.nom = nom;
	}





	public String getPrenom() {
		return prenom;
	}





	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}





	public String getEtat() {
		return etat;
	}





	public void setEtat(String etat) {
		this.etat = etat;
	}





	public String getLogin() {
		return login;
	}





	public void setLogin(String login) {
		this.login = login;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getSexe() {
		return sexe;
	}





	public void setSexe(String sexe) {
		this.sexe = sexe;
	}





	public Date getDateNaiss() {
		return dateNaiss;
	}





	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	
	
	
	
	
	

}
