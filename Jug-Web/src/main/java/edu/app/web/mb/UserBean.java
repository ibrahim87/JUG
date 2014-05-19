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
import edu.app.business.RoleServiceLocal;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Picture;
import edu.app.persistence.Role;
import edu.app.persistence.User;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {
@EJB
private AnotherEmailSenderRemote anotherEmailSenderRemote;
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private RoleServiceLocal roleServiceLocal;
	
	@EJB
	private PictureServiceLocal pictureServiceLocal;
	
	private Picture picture = new Picture();
	private static final long serialVersionUID = 6710404278650523921L;
	private User user = new User();
	
	private String password2;
	private String email2;
	private Role role;
	private String nameRole;
	private List<User> users = new ArrayList<User>();
	private List<User> picUsers = new ArrayList<User>();
	private boolean loggedIn = false;
	private boolean imJUGLeader = false;
	private boolean imJUGMember = false;
	private boolean skip;
	private boolean showFiledUpload = false;
	@SuppressWarnings("unused")
	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private String Email;
	private String mail ;

	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";


	public UserBean() {
	}

	@PostConstruct
	public void init() {
		users = userServiceLocal.findAllUser();
		picUsers = userServiceLocal.findByStatus("Refuser");
		loggedIn=false;
	
	}

	public List<User> allUsers() {
		users = userServiceLocal.findAllUser();
		return users;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
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

	public RoleServiceLocal getRoleServiceLocal() {
		return roleServiceLocal;
	}

	public void setRoleServiceLocal(RoleServiceLocal roleServiceLocal) {
		this.roleServiceLocal = roleServiceLocal;
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

	



	public StreamedContent getStreamedPic() {
		DefaultStreamedContent streamedPic = new DefaultStreamedContent(
				new ByteArrayInputStream(user.getPicture().getContent()),
				"image/png");
		return streamedPic;
	}

	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
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

	
public String getEmail() {
	return Email;
}

	public void setEmail(String email) {
	Email = email;
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
					if (user.getRole().getId() == 1) {
						navigateTo = "/pages/JUGLeader/Home";
						imJUGLeader = true;
					} else if (user.getRole().getId() == 2) {
						navigateTo = "/pages/JUGMember/Home";
						imJUGMember = true;
					
					}
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
		loggedIn =false;
		String navigateTo = null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
		
		navigateTo = "/index";
		return navigateTo;
	}

	public String createUser() {
		String navigateTo = null;
		role = roleServiceLocal.findByRoleName(nameRole);
		user.setRole(role);
		user.setEtat("attente");
		user.setPicture(picture);
		userServiceLocal.createUser(user);
		
		
		
		
		
	anotherEmailSenderRemote.sendMail(
				
				
				
				user.getMail(), "Register"," Hello Mr , and Mrs. felicitation you registered in our website, you have access to our site crossing   \n Your UserName is :=  " 
		       + user.getLogin()
				+ "\n Your Password is :=    "
	+ user.getPassword());
			
		FacesMessage msg = new FacesMessage(
				"Success! , Your inscription is Done ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		user = new User();
		navigateTo = "/index";
		return navigateTo;
		
	}
	
	public void forgetPassword (){
		anotherEmailSenderRemote.sendMail(user.getMail(), "  hello your user name is :   " , "    " + user.getLogin()+ "  \n your password is   "+user.getPassword());
		
		
		
		
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

		byte[] bytes =event.getFile().getContents();
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
	
	public void validateLogin(FacesContext context, UIComponent component, Object toValidate)
			throws ValidatorException {
		String login = null;
		if (toValidate instanceof String) {
			login = (String) toValidate;
		}
		if (login.isEmpty() || login == null) {
			return;
		}
		boolean loginAlreadyInUse = userServiceLocal.usernameExists(login);
		if (loginAlreadyInUse) {
			throw new ValidatorException(new FacesMessage("username already exist!"));
		}
	}
	public void validatePassword(FacesContext context, UIComponent component, Object toValidate)
			throws ValidatorException {
		
		if (password2!=user.getPassword()) {
			throw new ValidatorException(new FacesMessage("password not identical!"+user.getPassword()));
		}
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

	
	
	
}
