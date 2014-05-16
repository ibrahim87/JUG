package edu.app.persistence;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(
			this);

	private int id;
	private String name;

	private List<User> users;

	/**
	 * Instantiates a new role.
	 */
	public Role() {
		super();
	}

	
	public Role(PropertyChangeSupport changeSupport, int id, String name,
			List<User> users) {
		super();
		this.changeSupport = changeSupport;
		this.id = id;
		this.name = name;
		this.users = users;
	}


	@Id
	public int getId() {
		return this.id;

	}

	public void setId(int id) {
		int oldId = this.id;
		this.id = id;
		changeSupport.firePropertyChange("id", oldId, id);
	}

	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	public List<User> getUsers() {
		if (users == null)
			users = new ArrayList<User>();
		return users;
	}

	public void setUsers(List<User> users) {
		List<User> oldUsers = this.users;
		this.users = users;
		changeSupport.firePropertyChange("users", oldUsers, users);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		changeSupport.firePropertyChange("name", oldName, name);
	}

	public void adduser(User user) {
		user.setRole(this);
		this.getUsers().add(user);

	}

	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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

}
