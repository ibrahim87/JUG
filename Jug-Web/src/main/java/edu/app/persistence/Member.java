package edu.app.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
public class Member  extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
 public Member() {
	 super();
	 
 }
 
 
}
