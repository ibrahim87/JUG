package edu.app.persistence;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="t_contact") 
public class Contact  implements Serializable{
 private String type ;
 private String value ;
 private int idContact ;
private User user ;

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(
			this);


	
	
	public Contact() {
	}
	

	public Contact(String type, String value, int idContact, 
			PropertyChangeSupport changeSupport) {
		super();
		this.type = type;
		this.value = value;
		this.idContact = idContact;
	
		this.changeSupport = changeSupport;
	}

	
	@Override
	public String toString() {
		return "Contact [type=" + type + ", value=" + value + ", idContact="
				+ idContact + ", user="  + ", changeSupport="
				+ changeSupport + "]";
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((changeSupport == null) ? 0 : changeSupport.hashCode());
		result = prime * result + idContact;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (changeSupport == null) {
			if (other.changeSupport != null)
				return false;
		} else if (!changeSupport.equals(other.changeSupport))
			return false;
		if (idContact != other.idContact)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
	
			
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		String oldType = this.type;
		this.type = type;
		changeSupport.firePropertyChange("type", oldType, type);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		changeSupport.firePropertyChange("value", oldValue, value);
	}
	
	@Id
	public int getIdContact() {
		return idContact;
	}

	public void setIdContact(int idContact) {
		int oldIdContact = this.idContact;
		this.idContact = idContact;
		changeSupport.firePropertyChange("idContact", oldIdContact, idContact);
	}
	
	

	
	
	
	@Transient
	public PropertyChangeSupport getChangeSupport() {
		return changeSupport;
	}

	public void setChangeSupport(PropertyChangeSupport changeSupport) {
		this.changeSupport = changeSupport;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	@ManyToOne
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
}
