package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
@Entity(name="t_leader")
public class Leader  extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Leader() {
	}

}
