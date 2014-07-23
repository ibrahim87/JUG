//package edu.app.web.mb;
//
//import java.io.Serializable;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
//
//import edu.app.business.UserServiceLocal;
//import edu.app.persistence.Member;
//import edu.app.persistence.Speaker;
//import edu.app.persistence.User;
//
//@ManagedBean
//@ViewScoped
//public class ConfirmationBean implements Serializable{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@EJB
//	private UserServiceLocal userServiceLocal ;
//	
//	@ManagedProperty("#{userBean.user}")
//	private User user2;
//	private boolean imJUGLeader = false;
//	private boolean imJUGMember = false;
//	private boolean imSpeaker = false;
//	private User user;
//	private String message;
//
//	
//	
//
//
//@PostConstruct
//public String inti(){
//	
//	
//	HttpServletRequest req = (HttpServletRequest) FacesContext
//			.getCurrentInstance().getExternalContext().getRequest();
//
//	String cle = "";
//
//	cle = (String) req.getAttribute("cle");
//	
//	user=userServiceLocal.finduserbykey(cle);
//	
//
//	
//	String navigateTo = null;
//	
//	
//	if (user != null) {
//		
//	
//		setUser2(user);
//		
//		 if (user instanceof Member) {
//
//				
//				
//				navigateTo = "/pages/JUGMember/Home?faces-redirect=true";
//				imJUGMember = true;
//			}
//		 
//		else{
//		if (user instanceof Speaker) {
//
//			
//		
//			navigateTo = "/pages/JUGSpeaker/Home?faces-redirect=true";
//			imSpeaker = true;
//		}
//		
//		
//			
//		else {
//			message="";
//		}
//			
//			
//		return null;
//}}
//	return navigateTo;
//}
//	
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public User getUser2() {
//		return user2;
//	}
//
//	public void setUser2(User user2) {
//		this.user2 = user2;
//	}
//
//	public boolean isImJUGLeader() {
//		return imJUGLeader;
//	}
//
//	public void setImJUGLeader(boolean imJUGLeader) {
//		this.imJUGLeader = imJUGLeader;
//	}
//
//	public boolean isImJUGMember() {
//		return imJUGMember;
//	}
//
//	public void setImJUGMember(boolean imJUGMember) {
//		this.imJUGMember = imJUGMember;
//	}
//
//	public boolean isImSpeaker() {
//		return imSpeaker;
//	}
//
//	public void setImSpeaker(boolean imSpeaker) {
//		this.imSpeaker = imSpeaker;
//	}
//	
//	
//}
//
//
