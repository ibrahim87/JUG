package edu.app.business;

import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class AnotherEmailSender implements AnotherEmailSenderRemote {

	private Session session;

	/**
	 * Instantiates a new another email sender.
	 */
	public AnotherEmailSender() {
	}

	@PostConstruct
	public void init() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("company.esprit@gmail.com",
						"espritesprit");
			}
		});

	}

	@Override
	public void sendMail(String to, String subject, String messageBoby) {

		Message message = new MimeMessage(session);
		try {
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSentDate(new Date());
			message.setSubject(subject);
			message.setText(messageBoby);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
