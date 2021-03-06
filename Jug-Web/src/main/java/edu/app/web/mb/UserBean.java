package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import edu.app.business.AnotherEmailSenderRemote;
import edu.app.business.EMailLocal;
import edu.app.business.PictureServiceLocal;
import edu.app.business.SessionProvider;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Leader;
import edu.app.persistence.MD5;
import edu.app.persistence.Member;
import edu.app.persistence.Picture;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@ManagedBean(name = "userBean")
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

	@EJB
	private EMailLocal eMailLocal;

	private static final long serialVersionUID = 6710404278650523921L;
	private Picture picture = new Picture();
	private User user = new User();

	private String password2;
	private List<Member> AllMembersJUG;

	private String email2;
	private boolean formDisplayed = false;
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

	private String userType = "";

	private int selectedTypeUser = -1;;

	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private List<Speaker> speakers;

	private Member member = new Member();
	private Speaker newSpeaker = new Speaker();
	private List<Member> members = new ArrayList<Member>();
	private String nom;

	private String prenom;

	private String mail;

	private String etat;

	private String login;

	private String password;

	private String sexe;
	private String description;

	private Date dateNaiss;
	private String contact;
	private String nationality;
	private String job;
	private String company;
	private String blog;

	public UserBean() {
	}

	HttpServletRequest req;

	@PostConstruct
	public void init() {

		req = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		users = userServiceLocal.findAllUser();
		picUsers = userServiceLocal.findByStatus("Refuser");
		loggedIn = false;

		speakers = userServiceLocal.findAllSpeakers();
		AllMembersJUG = userServiceLocal.findAllMembersJUG();
	}

	public String AllUSERMember() {
		return "/pages/JUGMember/ListInscription?faces-redirect=true";
	}

	public String AllUSERMemberJUG() {
		return "/pages/JUGLeader/ListMemberJUG?faces-redirect=true";
	}

	public String AddEvent() {
		return "/pages/JUGLeader/AddEvent?faces-redirect=true";
	}

	public String AllEvent() {
		return "/pages/JUGLeader/AllEvent?faces-redirect=true";
	}

	public String AddCallForPaper() {
		return "/pages/JUGLeader/AddCallForPaper?faces-redirect=true";
	}

	public String AllProposal() {
		return "/pages/JUGLeader/AllProposal?faces-redirect=true";
	}

	public String MessagesSent() {
		return "/pages/both/messageSent?faces-redirect=true";
	}

	public String ComposerMessage() {
		return "/pages/both/messages?faces-redirect=true";
	}

	public String AllCallForPaper() {
		return "/pages/JUGLeader/AllCallForPaper?faces-redirect=true";
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

			} else {

				if (found.getEtat().equals("Refuser")) {
					FacesMessage message = new FacesMessage(
							"Registredin refused ! ");
					FacesContext.getCurrentInstance().addMessage(null, message);

				} else {

					user = found;
					loggedIn = true;
					sessionProvider.setConnectedUser(user);

					if (user instanceof Leader) {

						setUserType("Leader");
						System.out.println("  " + user.getNom());
						navigateTo = "/pages/JUGLeader/Home?faces-redirect=true";
						imJUGLeader = true;

					} else if (user instanceof Member) {

						setUserType("Member");
						System.out.println("  " + user.getNom());
						navigateTo = "/pages/JUGMember/Home?faces-redirect=true";
						imJUGMember = true;
					} else {
						if (user instanceof Speaker) {

							setUserType("Speaker");
							System.out.println("  " + user.getNom());
							navigateTo = "/pages/JUGSpeaker/Home?faces-redirect=true";
							imSpeaker = true;
						}
					}

					// user = found;
					// loggedIn = true;
					// sessionProvider.setConnectedUser(user);
				}
			}
		} else {
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

		navigateTo = "/index?faces-redirect=true";
		return navigateTo;
	}

	public String cretNewUser() {
		String navigateTo = null;

		if (selectedTypeUser == 1) {

			user = new Speaker();
		}

		if (selectedTypeUser == 2) {

			user = new Member();
		}
		user.setNationality("No Completed");
		user.setJob("No Completed");
		user.setLogin(login);
		user.setPicture(picture);
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setMail(mail);
		user.setPassword(password);
		user.setCompany("No Completed");
		user.setContact("No Completed");

		user.setSexe(sexe);
		user.setDescription(description);
		user.setEtat("attente");
		user.setBlog("No Completed");

		MD5 md5 = new MD5();
		String code = md5.generate();

		String cryp = md5.getEncodedPassword(code);
		String url = "http://" + req.getHeader("host") + req.getContextPath()
				+ "/Confirmation?userkey=" + code + "?" + cryp;
		user.setUserkey(code);
		userServiceLocal.createUser(user);
		eMailLocal.sendMail(user.getMail(), "URL \n " + url);

		selectedTypeUser = -1;

		// anotherEmailSenderRemote.sendMail(
		//
		//
		//
		// user.getMail(),
		// "Register"," Hello Mr , and Mrs. felicitation you registered in our website, \n you have access to our site crossing   \n Your UserName is :=  "
		// + user.getLogin()
		// + "\n Your Password is :=    "
		// + user.getPassword());

		formDisplayed = true;

		FacesMessage msg = new FacesMessage(
				"Success! , Your inscription is Done ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		user = new User();

		return navigateTo = "/index?faces-redirect=true";

	}

	public void doCancel() {

		formDisplayed = false;
	}

	public void updateMember() {

		member.setEtat("Accepter");
		userServiceLocal.updateUser(member);

	}

	// public String deleteMember() {
	// member = (Member) dataModel.getRowData();
	//
	// member.setEtat("refuse");
	// userServiceLocal.updateUser(member);
	// return "";
	// }

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

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Speaker> speakers) {
		this.speakers = speakers;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Speaker getNewSpeaker() {
		return newSpeaker;
	}

	public void setNewSpeaker(Speaker newSpeaker) {
		this.newSpeaker = newSpeaker;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCompany() {
		return company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public List<Member> getAllMembersJUG() {
		return AllMembersJUG;
	}

	public void setAllMembersJUG(List<Member> allMembersJUG) {
		AllMembersJUG = allMembersJUG;
	}

	public EMailLocal geteMailLocal() {
		return eMailLocal;
	}

	public void seteMailLocal(EMailLocal eMailLocal) {
		this.eMailLocal = eMailLocal;
	}

}
