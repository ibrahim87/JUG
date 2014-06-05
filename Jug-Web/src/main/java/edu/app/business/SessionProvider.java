package edu.app.business;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import edu.app.persistence.User;


@ManagedBean(name="SP",eager=true)
@SessionScoped
public class SessionProvider implements Serializable{
	
	
	private User connectedUser;

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

}
