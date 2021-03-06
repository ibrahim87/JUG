package edu.app.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * Entity implementation class for Entity: User.
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {

	@JsonIgnore
	private int idUser;

	private String nom;

	private String prenom;

	@JsonIgnore
	private String mail;

	@JsonIgnore
	private String etat;

	@JsonIgnore
	private String login;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private String sexe;
	
	private String contact;
	@JsonIgnore
	private String nationality;
	@JsonIgnore
	private String job;
	@JsonIgnore
	private String company;
	@Column(length = 50000)
	private String description;
	@JsonIgnore
	private String blog;
	@JsonIgnore
	private Picture picture;
	
	private List<Article> articles;
	@JsonIgnore
	private String userkey;
	
	private String urlphotoSpeaker;
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}

	/**
	 * Gets the id user.
	 * 
	 * @return the id user
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getidUser() {
		return this.idUser;
	}

	/**
	 * Sets the id user.
	 * 
	 * @param idUser
	 *            the new id user
	 */
	public void setidUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * Gets the nom.
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Sets the nom.
	 * 
	 * @param nom
	 *            the new nom
	 */
	public void setNom(String nom) {

		this.nom = nom;

	}

	/**
	 * Gets the prenom.
	 * 
	 * @return the prenom
	 */

	/**
	 * Gets the mail.
	 * 
	 * @return the mail
	 */

	public String getMail() {
		return this.mail;
	}

	/**
	 * Sets the mail.
	 * 
	 * @param mail
	 *            the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the etat.
	 * 
	 * @return the etat
	 */
	public String getEtat() {
		return this.etat;
	}

	/**
	 * Sets the etat.
	 * 
	 * @param etat
	 *            the new etat
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Gets the login.
	 * 
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Sets the login.
	 * 
	 * @param login
	 *            the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the sexe.
	 * 
	 * @return the sexe
	 */
	public String getSexe() {
		return this.sexe;
	}

	/**
	 * Sets the sexe.
	 * 
	 * @param sexe
	 *            the new sexe
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * Gets the date naiss.
	 * 
	 * @return the date naiss
	 */

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;

		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom
				+ "]";
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "picture_fk", unique = true)
	@JsonIgnore
	public Picture getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 * 
	 * @param picture
	 *            the new picture
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	@JsonIgnore
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
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

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getUrlphotoSpeaker() {
		return urlphotoSpeaker;
	}

	public void setUrlphotoSpeaker(String urlphotoSpeaker) {
		this.urlphotoSpeaker = urlphotoSpeaker;
	}

}
