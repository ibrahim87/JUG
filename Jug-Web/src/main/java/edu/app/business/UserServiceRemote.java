package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Leader;
import edu.app.persistence.Member;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@Remote
public interface UserServiceRemote {
	
	void createUser(User user);

	void updateUser(User user);

	void removeUser(User user);

	List<User> findAllUser();
	
	List<User> findByKeywordUser(String keyword);
	
	User findByUserLogin(String nom);
	
	public User findByLoginAndPass(String login, String password);
	
	public List<User> findByKeywordLogin(String login);
	public List<User> findByKeywordLastname(String lastName);

	public List<User> findByStatus(String status);
	public List<User> findByParameter(String param);
	
	public List<User> findByKeywordLastNameAndLogin(String lastName,
			String login);
	
	public void removeManyUser(List<User> users);

	public User findByUserMail(String name);
	
	public List<User> findByKeywordLastNameAndStatus(String lastName,
			String status);
	public boolean usernameExists(String login);
	
	List<Speaker> findAllSpeakers();
	public List<Member> findAllMembers(String etat);

	public List<Leader> findAllLeaders();
	

	public List<Member> findAllMembersJUG();
	
	

	
	
	
	
}
