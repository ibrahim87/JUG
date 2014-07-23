package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name = "t_event")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@XmlRootElement
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	@Column(length=50000)
	private String description;
	private Date startDate;
	
	private Date endDate;
	private int numberOfPlace;
	private Date startliveTalk;
	private Date endliveTalk;
	private String lienHungOut = null;
	private CallForPaper callForPaper;
	private List<Attended> attendeds;
	private List<Picture>pictures;
	private Picture logo;
	//private Picture picture; 
	
	
	
	public Event(String title) {
		super();
		this.title = title;
	}

	public Event() {

	}

	public Event(String title, String description, Date startDate,
			Date endDate, int numberOfPlace, Date startliveTalk,
			Date endliveTalk, String lienHungOut, 
			CallForPaper callForPaper) {
		super();
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberOfPlace = numberOfPlace;
		this.startliveTalk = startliveTalk;
		this.endliveTalk = endliveTalk;
		this.lienHungOut = lienHungOut;

		
		this.callForPaper = callForPaper;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
  
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getNumberOfPlace() {
		return numberOfPlace;
	}

	public void setNumberOfPlace(int numberOfPlace) {
		this.numberOfPlace = numberOfPlace;
	}

	public Date getStartliveTalk() {
		return startliveTalk;
	}

	public void setStartliveTalk(Date startliveTalk) {
		this.startliveTalk = startliveTalk;
	}

	public Date getEndliveTalk() {
		return endliveTalk;
	}

	public void setEndliveTalk(Date endliveTalk) {
		this.endliveTalk = endliveTalk;
	}

	public String getLienHungOut() {
		return lienHungOut;
	}

	public void setLienHungOut(String lienHungOut) {
		this.lienHungOut = lienHungOut;
	}



	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "callofpaper_fk", unique = true)
	public CallForPaper getCallForPaper() {
		return callForPaper;
	}

	public void setCallForPaper(CallForPaper callForPaper) {
		this.callForPaper = callForPaper;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToMany( mappedBy = "events")
	@JsonIgnore
	public List<Attended> getAttendeds() {
		return attendeds;
	}

	public void setAttendeds(List<Attended> attendeds) {
		this.attendeds = attendeds;
	}

	
	
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "picture_fk", unique = true)
//	public Picture getPicture() {
//		return picture;
//	}
//
//	public void setPicture(Picture picture) {
//		this.picture = picture;
//	}

	@Override
	public String toString() {
		return title;
	}
	
	@OneToMany(mappedBy ="event", cascade=CascadeType.ALL)
	@JsonIgnore
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}


	public void addPictures(List<Picture> pictures){
		for(Picture picture:pictures)
			picture.setEvent(this);
		this.pictures=pictures;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "picture_fk", unique = true)
	@JsonIgnore
	public Picture getLogo() {
		return logo;
	}

	public void setLogo(Picture logo) {
		this.logo = logo;
	}

}
