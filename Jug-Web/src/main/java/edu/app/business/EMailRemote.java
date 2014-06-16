package edu.app.business;

import javax.ejb.Remote;

// TODO: Auto-generated Javadoc
/**
 * The Interface EMailRemote.
 */
@Remote
public interface EMailRemote {
	
	/**
	 * Send mail.
	 *
	 * @param recipient the recipient
	 * @param text the text
	 */
	public void sendMail(String recipient, String text);
}
