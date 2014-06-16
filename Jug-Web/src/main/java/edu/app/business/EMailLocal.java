package edu.app.business;

import javax.ejb.Local;

@Local
public interface EMailLocal {
	/**
	 * Send mail.
	 *
	 * @param recipient the recipient
	 * @param text the text
	 */
	public void sendMail(String recipient, String text);
}
