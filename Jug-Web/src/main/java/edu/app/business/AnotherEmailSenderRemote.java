package edu.app.business;

import javax.ejb.Remote;

// TODO: Auto-generated Javadoc
/**
 * The Interface AnotherEmailSenderRemote.
 */
@Remote
public interface AnotherEmailSenderRemote {
	
	/**
	 * Send mail.
	 *
	 * @param to the to
	 * @param subject the subject
	 * @param messageBoby the message boby
	 */
	void sendMail(String to, String subject, String messageBoby);
}
