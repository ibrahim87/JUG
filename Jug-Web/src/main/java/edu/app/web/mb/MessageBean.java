package edu.app.web.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import edu.app.business.EMailLocal;
import edu.app.business.MessageAnswerServiceLocal;
import edu.app.business.MessageServiceLocal;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Message;
import edu.app.persistence.User;

@ManagedBean(name = "messageBean")
@ViewScoped
public class MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8242820100344794929L;
	@EJB
	private MessageServiceLocal messageServiceLocal;
	@EJB
	private UserServiceLocal userServiceLocal;
	@EJB
	private MessageAnswerServiceLocal messageAnswerServiceLocal;
	@EJB
	private EMailLocal eMailLocal;

	@ManagedProperty(value = "#{userBean.user}")
	private User sender = new User();
	private User receiver = new User();
	private String receiverName, text;
	private Message message = new Message();
	private List<Message> sentMessages = new ArrayList<Message>();
	private List<Message> receivedMessages = new ArrayList<Message>();
	private List<Message> messagesUnread = new ArrayList<Message>();
	private int nbMessageUnread;

	private boolean showMessageDetail = false;

	private List<SelectItem> selectUsers = new ArrayList<SelectItem>();
	private int selectedUserId = -1;

	public MessageBean() {
	}

	@PostConstruct
	public void init() {
		sentMessages = messageServiceLocal.findMessagebySender(sender);
		receivedMessages = messageServiceLocal.findMessagebyReceiver(sender);
	}

	public MessageServiceLocal getMessageServiceLocal() {
		return messageServiceLocal;
	}

	public void setMessageServiceLocal(MessageServiceLocal messageServiceLocal) {
		this.messageServiceLocal = messageServiceLocal;
	}

	public UserServiceLocal getUserServiceLocal() {
		return userServiceLocal;
	}

	public void setUserServiceLocal(UserServiceLocal userServiceLocal) {
		this.userServiceLocal = userServiceLocal;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public List<Message> getSentMessages() {
		sentMessages = messageServiceLocal.findMessagebySender(sender);
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Message> getReceivedMessages() {
		receivedMessages = messageServiceLocal.findMessagebyReceiver(sender);

		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public boolean isShowMessageDetail() {
		return showMessageDetail;
	}

	public void setShowMessageDetail(boolean showMessageDetail) {
		this.showMessageDetail = showMessageDetail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Message> getMessagesUnread() {
		messagesUnread = messageServiceLocal.findAssignsByReceiverAndStatus(
				sender, false);
		return messagesUnread;
	}

	public void setMessagesUnread(List<Message> messagesUnread) {
		this.messagesUnread = messagesUnread;
	}

	public int getNbMessageUnread() {
		messagesUnread = messageServiceLocal.findAssignsByReceiverAndStatus(
				sender, false);
		
		nbMessageUnread = messagesUnread.size();
		
		
	
		return nbMessageUnread;
		
		
		
	}

	public void setNbMessageUnread(int nbMessageUnread) {
		this.nbMessageUnread = nbMessageUnread;
	}

	public List<SelectItem> getSelectUsers() {
		selectUsers.clear();
		List<User> usersAccepted = userServiceLocal.findByStatus("Accepter");
		int i = usersAccepted.indexOf(sender);
		usersAccepted.remove(i);
		for (User user : usersAccepted)
			selectUsers.add(new SelectItem(user.getidUser(), user.getLogin()));
		return selectUsers;
	}

	public void setSelectUsers(List<SelectItem> selectUsers) {
		this.selectUsers = selectUsers;
	}

	public int getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(int selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	// methods
	public void sendMessage() {
		receiver = userServiceLocal.findUserById(selectedUserId);
		messageServiceLocal.sendMessage(sender, receiver, message.getMessage(),
				false, new Date(), message.getSubject(), new Date());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Message Sent!"));
		message = new Message();

	}

	public void sendMessageAnswer() {
		messageServiceLocal.sendMessage(message.getReceiver(),
				message.getSender(), text, false, new Date(),
				"Response for message : " + message.getSubject(), new Date());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Message Sent!"));
		message = new Message();
		text = "";
	}

	public void sendMessageGmail() {
		User user = userServiceLocal.findUserById(selectedUserId);
		eMailLocal.sendMail(user.getMail(), message.getMessage());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Mail Sent!"));
		message = new Message();

	}

	public void changeMessageStatus() {
		message.setEtat(true);
		messageServiceLocal.updateMessage(message);

	}

}
