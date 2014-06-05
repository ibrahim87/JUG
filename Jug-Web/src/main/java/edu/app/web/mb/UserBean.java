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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.AnotherEmailSenderRemote;
import edu.app.business.PictureServiceLocal;
import edu.app.business.SessionProvider;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Leader;
import edu.app.persistence.Member;
import edu.app.persistence.Picture;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {
	
	@EJB
	private AnotherEmailSenderRemote anotherEmailSenderRemote;
	@EJB
	private UserServiceLocal userServiceLocal;

	@EJB
	private PictureServiceLocal pictureServiceLocal;

	
	@ManagedProperty("#{SP}")
	private SessionProvider sessionProvider;
	
	private static final long serialVersionUID = 6710404278650523921L;
	private Picture picture = new Picture();
	private User user = new User();

	private String password2;

	private String email2;

	private List<User> users = new ArrayList<User>();
	private List<User> picUsers = new ArrayList<User>();
	private boolean loggedIn = false;
	private boolean imJUGLeader = false;
	private boolean imJUGMember = false;
	private boolean imSpeaker = false;
	private User newuser = new User();
	private boolean skip;
	private boolean showFiledUpload = false;

	@SuppressWarnings("unused")
	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;

	private String mail;
	private String userType = "";

	private int selectedTypeUser = -1;

	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private String nom;
	private String prenom;
	private String sexe;

	

	public UserBean() {
	}

	@PostConstruct
	public void init() {
		users = userServiceLocal.findAllUser();
		picUsers = userServiceLocal.findByStatus("Refuser");
		loggedIn = false;

	}

	
	



	
	// methods
	public String doLogin() throws IOException {
		String navigateTo = null;

		User found = userServiceLocal.findByLoginAndPass(user.getLogin(),
				user.getPassword());
		if (found != null) {
			if (found.getEtat().equals("attente")) {
				FacesMessage message = new FacesMessage(
						"Registredin waiting ! ");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else 
				if (found.getEtat().equals("Refuser")) {
					FacesMessage message = new FacesMessage(
							"Registredin refused ! ");
					FacesContext.getCurrentInstance().addMessage(null, message);

				} else

				if (user instanceof Leader) {
					

					setUserType("Leader");
					System.out.println("  " + user.getNom());
					navigateTo = "/pages/JUGLeader/Home";
					imJUGLeader = true;
				}
				

				if (user instanceof Member) {
						
					setUserType("Member");
					System.out.println("  " + user.getNom());
					navigateTo = "/pages/JUGMember/Home";
					imJUGMember = true;
				}
				

				if (user instanceof Speaker) {

					setUserType("Speaker");
					System.out.println("  " + user.getNom());
					navigateTo = "/pages/JUGSpeaker/Home";
					imSpeaker = true;
				}
				
				user = found;
				loggedIn = true;
				sessionProvider.setConnectedUser(user);
				

			}
		 else {
			FacesMessage message = new FacesMessage("Bad credentials ! ");
			FacesContext.getCurrentInstance().addMessage(null, message);
			navigateTo = null;
			loggedIn = false;
		}
		return navigateTo;
	}

	public String logout() {
		loggedIn = false;
		String navigateTo = null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();

		navigateTo = "/index";
		return navigateTo;
	}

	public String createUser() {
		
		String navigateTo = null;

		if (selectedTypeUser == 1) {

			newuser = new Member();

		}

		if (selectedTypeUser == 2) {

			newuser = new Speaker();
			
		
		}
		
		
		
		newuser.setPicture(picture);
		newuser.setEtat("attente");
		userServiceLocal.createUser(newuser);
		 selectedTypeUser = -1;
	

		FacesMessage msg = new FacesMessage(
				"Success! , Your inscription is Done ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		newuser = new User();
		navigateTo = "/index";
		return navigateTo;

	}

	public void forgetPassword() {
		anotherEmailSenderRemote.sendMail(user.getMail(),
				"  hello your user name is :   ", "    " + user.getLogin()
						+ "  \n your password is   " + user.getPassword());

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

	public String onFlowProcess(FlowEvent event) {

		if (skip) {
			skip = false;
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public void update() {
		userServiceLocal.updateUser(user);
		FacesMessage msg = new FacesMessage("Update! , Update done successfuly");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// goups

	public void validateLogin(FacesContext context, UIComponent component,
			Object toValidate) throws ValidatorException {
		String login = null;
		if (toValidate instanceof String) {
			login = (String) toValidate;
		}
		if (login.isEmpty() || login == null) {
			return;
		}
		boolean loginAlreadyInUse = userServiceLocal.usernameExists(login);
		if (loginAlreadyInUse) {
			throw new ValidatorException(new FacesMessage(
					"username already exist!"));
		}
	}

	public void validatePassword(FacesContext context, UIComponent component,
			Object toValidate) throws ValidatorException {

		if (password2 != user.getPassword()) {
			throw new ValidatorException(new FacesMessage(
					"password not identical!" + user.getPassword()));
		}
	}

	public List<User> allUsers() {
		users = userServiceLocal.findAllUser();
		return users;
	}

	public StreamedContent getStreamedPic() {
		DefaultStreamedContent streamedPic = new DefaultStreamedContent(
				new ByteArrayInputStream(user.getPicture().getContent()),
				"image/png");
		return streamedPic;
	}
	
	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
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

	public List<User> getPicUsers() {
		return picUsers;
	}

	public void setPicUsers(List<User> picUsers) {
		this.picUsers = picUsers;
	}

	public String getDestinationTemp() {
		return destinationTemp;
	}

	public void setDestinationTemp(String destinationTemp) {
		this.destinationTemp = destinationTemp;
	}

	public boolean isShowFiledUpload() {
		return showFiledUpload;
	}

	public void setShowFiledUpload(boolean showFiledUpload) {
		this.showFiledUpload = showFiledUpload;
	}

	public boolean isImJUGLeader() {
		return imJUGLeader;
	}

	public void setImJUGLeader(boolean imJUGLeader) {
		this.imJUGLeader = imJUGLeader;
	}

	public boolean isImJUGMember() {
		return imJUGMember;
	}

	public void setImJUGMember(boolean imJUGMember) {
		this.imJUGMember = imJUGMember;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isImSpeaker() {
		return imSpeaker;
	}

	public void setImSpeaker(boolean imSpeaker) {
		this.imSpeaker = imSpeaker;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getSelectedTypeUser() {
		return selectedTypeUser;
	}

	public void setSelectedTypeUser(int selectedTypeUser) {
		this.selectedTypeUser = selectedTypeUser;
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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	

	public User getNewuser() {
		return newuser;
	}

	public void setNewuser(User newuser) {
		this.newuser = newuser;
	}
	
	public void setSessionProvider(SessionProvider sessionProvider) {
		this.sessionProvider = sessionProvider;
	}
	
	public SessionProvider getSessionProvider() {
		return sessionProvider;
	}
	
}
