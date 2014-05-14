package edu.app.web.mb;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.app.business.AuthentificationLocal;
import edu.app.persistence.User;

@ManagedBean(name = "authBean")
@SessionScoped
public class AuthentificationBean implements Serializable {

	private static final long serialVersionUID = 1633978142062982603L;
	@EJB
private AuthentificationLocal  authentificationLocal;
	
private User user =new User();
private boolean loggedIn=false;

	
	public AuthentificationBean() {
				
	}
	public String DoLogin() {
		String navigateTo = null;
User found=authentificationLocal.authenticate(user.getLogin(), user.getPassword());
		if (found!=null) {
			user=found;
			setLoggedIn(true);
				if (user.getRole().getId()==4) {
					navigateTo ="/pages/customer/home";
					}
		else 
			if (user.getRole().getId()==1) {
				navigateTo ="/pages/admin/Home";
				}
	
		}else {
			FacesMessage message= new FacesMessage("bad credentials");
			FacesContext.getCurrentInstance().addMessage("login_form:login_submit", message);
			
			setLoggedIn(false);
			navigateTo = null;
		}
		return navigateTo;
	}
public String logout(){
	String navigateTo=null;
//	setLoggedIn(false);
//	user=new User();
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
	navigateTo="/index";
	return navigateTo;
}


	public AuthentificationLocal getAuthentificationLocal() {
		return authentificationLocal;
	}



	public void setAuthentificationLocal(AuthentificationLocal authentificationLocal) {
		this.authentificationLocal = authentificationLocal;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	
}
