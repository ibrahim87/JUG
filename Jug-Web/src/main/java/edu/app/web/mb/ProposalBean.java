package edu.app.web.mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



import edu.app.business.CallOfPaperServiceLocal;
import edu.app.business.PictureServiceLocal;
import edu.app.business.PropositionServiceLocal;
import edu.app.business.SessionProvider;
import edu.app.persistence.CallForPaper;
import edu.app.persistence.Picture;
import edu.app.persistence.Proposition;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

@ManagedBean(name = "proposalBean")
@SessionScoped
public class ProposalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PropositionServiceLocal propositionServiceLocal;

	@EJB
	private PictureServiceLocal pictureServiceLocal;
	
	@EJB
	private CallOfPaperServiceLocal callOfPaperServiceLocal;
	@ManagedProperty("#{SP}")
	private SessionProvider sessionProvider;

	private Proposition newproposal = new Proposition();
	private User user;
	private Speaker speaker;
	private List<Proposition> propositions;
	private CallForPaper callForPaper;
	private StreamedContent streamedPic;
	private DefaultStreamedContent streamedPicture;
	private String destinationTemp = "E:\\jee\\servers\\s05\\jboss-as-7.1.1\\welcome-content\\temp\\";
	private Picture picture = new Picture();
	private Proposition proposition = new Proposition();
	private List<Proposition>propositionsAccepted;
	private List<Proposition>propositionsNotAccepted;
	private Proposition propo;
	private byte[] pictures;
	@PostConstruct
	public void init() {
		
		 pictures = pictureServiceLocal.findPictureById(31).getContent();

			if (picture != null)
				streamedPic = new DefaultStreamedContent(new ByteArrayInputStream(
						pictures));
			

	 propositions =propositionServiceLocal.findAllProposition();
	 propositionsAccepted=propositionServiceLocal.findAllPropositionAccepted();
	 propositionsNotAccepted=propositionServiceLocal.findAllPropositionNotAccepted();
		 
	}

	public ProposalBean() {
	}

	public String NewProposal() {
		String navigateTo = null;
		speaker = (Speaker) sessionProvider.getConnectedUser();
		// user = sessionProvider.getConnectedUser();
		newproposal.setEtatPro("attente");
		newproposal.setSpeaker(speaker);
    int year=new Date().getYear();
    year=year+1900;	    	
	System.out.println("yeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+year);
	newproposal.setCallForPaper(callOfPaperServiceLocal.findCallForPaperBYName(String.valueOf(year)));
	
	
	propositionServiceLocal.createProposition(newproposal);

		

		newproposal = new Proposition();
		
		
		FacesMessage message = new FacesMessage("Success! ! ");
		FacesContext.getCurrentInstance().addMessage(null, message);

		navigateTo = "/pages/CallOfPaper?faces-redirect=true";
		
		
		return navigateTo;

	}
	public String AllProposalAccepted() {
		return "/pages/JUGLeader/AllProposalA?faces-redirect=true";
	}
	public String AllProposalNotAccepted() {
		return "/pages/JUGLeader/AllProposalNot?faces-redirect=true";
	}
	
	public String deletePro() {
		user = sessionProvider.getConnectedUser();
		
		propositionServiceLocal.deleteProposition(proposition);
		propositions.remove(proposition);
		
		proposition= new Proposition();

		return null;
	}
	
	public void deleteProposal(Proposition proposition) {
		user = sessionProvider.getConnectedUser();
		proposition= new Proposition();
			propositionServiceLocal.deleteProposition(proposition);
		
		
			
			FacesMessage message = new FacesMessage("delete ! ");
			FacesContext.getCurrentInstance().addMessage(null, message);
			
			
		}
		
	
	public void updateProposal() {
		user = sessionProvider.getConnectedUser();
		
		proposition.setEtatPro("Accepter");
		
		propositionServiceLocal.updateProposition(proposition);
		propositions=propositionServiceLocal.findAllProposition();
			
		FacesMessage message = new FacesMessage("update  ! ");
		FacesContext.getCurrentInstance().addMessage(null, message);
		

		}
	
	
	
	public PropositionServiceLocal getPropositionServiceLocal() {
		return propositionServiceLocal;
	}

	public void setPropositionServiceLocal(
			PropositionServiceLocal propositionServiceLocal) {
		this.propositionServiceLocal = propositionServiceLocal;
	}

	public SessionProvider getSessionProvider() {
		return sessionProvider;
	}

	public void setSessionProvider(SessionProvider sessionProvider) {
		this.sessionProvider = sessionProvider;
	}

	public Proposition getNewproposal() {
		return newproposal;
	}

	public void setNewproposal(Proposition newproposal) {
		this.newproposal = newproposal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public List<Proposition> getPropositions() {
		return propositions;
	}

	public void setPropositions(List<Proposition> propositions) {
		this.propositions = propositions;
	}

	public CallForPaper getCallForPaper() {
		return callForPaper;
	}

	public void setCallForPaper(CallForPaper callForPaper) {
		this.callForPaper = callForPaper;
	}

	public CallOfPaperServiceLocal getCallOfPaperServiceLocal() {
		return callOfPaperServiceLocal;
	}

	public void setCallOfPaperServiceLocal(
			CallOfPaperServiceLocal callOfPaperServiceLocal) {
		this.callOfPaperServiceLocal = callOfPaperServiceLocal;
	}

	public Proposition getProposition() {
		return proposition;
	}

	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}

	public PictureServiceLocal getPictureServiceLocal() {
		return pictureServiceLocal;
	}

	public void setPictureServiceLocal(PictureServiceLocal pictureServiceLocal) {
		this.pictureServiceLocal = pictureServiceLocal;
	}

	public StreamedContent getStreamedPic() {
		return streamedPic;
	}

	public void setStreamedPic(StreamedContent streamedPic) {
		this.streamedPic = streamedPic;
	}

	public DefaultStreamedContent getStreamedPicture() {
		return streamedPicture;
	}

	public void setStreamedPicture(DefaultStreamedContent streamedPicture) {
		this.streamedPicture = streamedPicture;
	}

	public String getDestinationTemp() {
		return destinationTemp;
	}

	public void setDestinationTemp(String destinationTemp) {
		this.destinationTemp = destinationTemp;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public byte[] getPictures() {
		return pictures;
	}

	public void setPictures(byte[] pictures) {
		this.pictures = pictures;
	}

	public List<Proposition> getPropositionsAccepted() {
		return propositionsAccepted;
	}

	public void setPropositionsAccepted(List<Proposition> propositionsAccepted) {
		this.propositionsAccepted = propositionsAccepted;
	}

	public List<Proposition> getPropositionsNotAccepted() {
		return propositionsNotAccepted;
	}

	public void setPropositionsNotAccepted(List<Proposition> propositionsNotAccepted) {
		this.propositionsNotAccepted = propositionsNotAccepted;
	}

	public Proposition getPropo() {
		return propo;
	}

	public void setPropo(Proposition propo) {
		this.propo = propo;
	}

}
