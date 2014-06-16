package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Message;
import edu.app.persistence.MessageAnswer;



@Remote
public interface MessageAnswerServiceRemote {

	public List<MessageAnswer> findMessageAnswerbyMessage(Message message);

	public List<MessageAnswer> findMessageAnswerbyName(String msg);

	public void updateMessageAnswer(MessageAnswer MessageAnswer);

	public void removeMessageAnswer(MessageAnswer MessageAnswer);

	public void createMessageAnswer(MessageAnswer MessageAnswer);
}
