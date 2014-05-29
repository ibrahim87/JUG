package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "t_event")
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int numberOfPlace;
	private Date startliveTalk;
	private Date endliveTalk;
	private String lienHungOut = null;

	private List<Picture> pictures;
	private CallForPaper callForPaper;
	private List<Attended> attendeds;
	public Event() {

	}

	public Event(String title, String description, Date startDate,
			Date endDate, int numberOfPlace, Date startliveTalk,
			Date endliveTalk, String lienHungOut, List<Picture> pictures,
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

		this.pictures = pictures;
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

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	@OneToOne(cascade = CascadeType.ALL)
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
	
	@ManyToMany
	public List<Attended> getAttendeds() {
		return attendeds;
	}

	public void setAttendeds(List<Attended> attendeds) {
		this.attendeds = attendeds;
	}

}
