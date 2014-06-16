package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Message;
import edu.app.persistence.MessageAnswer;



@Local
public interface MessageAnswerServiceLocal {
	public List<MessageAnswer> findMessageAnswerbyMessage(Message message);

	public List<MessageAnswer> findMessageAnswerbyName(String msg);

	public void updateMessageAnswer(MessageAnswer MessageAnswer);

	public void removeMessageAnswer(MessageAnswer MessageAnswer);

	public void createMessageAnswer(MessageAnswer MessageAnswer);
}
