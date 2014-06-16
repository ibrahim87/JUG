package edu.app.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MessagePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -451304555741074570L;
	private int idSender;

	private int idReceiver;

	private Date date;

	public MessagePK() {
		// TODO Auto-generated constructor stub
	}

	public MessagePK(int idSender, int idReceiver, Date date) {
		super();
		this.idSender = idSender;
		this.idReceiver = idReceiver;
		this.date = date;
	}

	@Column(name = "sender_fk")
public int getIdSender() {
	return idSender;
}
	public void setIdSender(int idSender) {
		this.idSender = idSender;
	}

	@Column(name = "receiver_fk")
	public int getIdReceiver() {
	return idReceiver;
}
	public void setIdReceiver(int idReceiver) {
		this.idReceiver = idReceiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idReceiver;
		result = prime * result + idSender;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessagePK other = (MessagePK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idReceiver != other.idReceiver)
			return false;
		if (idSender != other.idSender)
			return false;
		return true;
	}

}
