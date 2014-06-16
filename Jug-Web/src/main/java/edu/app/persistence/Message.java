package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * Entity implementation class for Entity: Assign.
 */
@Entity
@Table(name = "t_message")
public class Message implements Serializable {

	private MessagePK messagePK;

	private String message;

	private boolean etat;

	private User sender;

	private User receiver;

	private String subject;
	private Date updatDate;
	private List<MessageAnswer> messageAnswers;
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new assign course.
	 */
	public Message() {
		super();
	}

	public Message(Date date, String message, boolean etat, User sender,
			User receiver, String subject, Date updateDate) {
		this.getMessagePK().setIdSender(sender.getidUser());
		this.getMessagePK().setIdReceiver(receiver.getidUser());
		this.getMessagePK().setDate(date);
		this.message = message;
		this.etat = etat;
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.updatDate = updateDate;
	}

	@ManyToOne
	@JoinColumn(name = "sender_fk", insertable = false, updatable = false)
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@ManyToOne
	@JoinColumn(name = "receiver_fk", insertable = false, updatable = false)
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@EmbeddedId
	public MessagePK getMessagePK() {
		if (messagePK == null)
			messagePK = new MessagePK();
		return messagePK;
	}

	public void setMessagePK(MessagePK messagePK) {
		this.messagePK = messagePK;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return "Message [message=" + message + ", etat=" + etat + "]";
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getUpdatDate() {
		return updatDate;
	}

	public void setUpdatDate(Date updatDate) {
		this.updatDate = updatDate;
	}

	@OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
	public List<MessageAnswer> getMessageAnswers() {
		return messageAnswers;
	}

	public void setMessageAnswers(List<MessageAnswer> messageAnswers) {
		this.messageAnswers = messageAnswers;
	}

}
