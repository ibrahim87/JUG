package edu.app.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Message;
import edu.app.persistence.User;


@Local
public interface MessageServiceLocal {

	public void sendMessage(User sender, User receiver, String msg,
			boolean etat, Date date, String subject, Date updateDate);

	public List<Message> findMessagebySender(User sender);

	public List<Message> findMessagebyReceiver(User receiver);

	public List<Message> findMessagebyName(String msg);

	public List<Message> findAssignsBySenderAndReceiver(User sender,
			User receiver);

	public void updateMessage(Message message);

	public void removeMessage(Message message);

	public List<Message> findAssignsByReceiverAndStatus(User receiver,
			boolean etat);
	List<Message> findAssignsBySenderAndStatus(User sender,
			boolean etat);
}
