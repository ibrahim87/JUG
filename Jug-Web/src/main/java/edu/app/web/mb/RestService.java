package edu.app.web.mb;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.app.business.ArticleServiceLocal;
import edu.app.business.EventServiceLocal;
import edu.app.business.UserServiceLocal;
import edu.app.persistence.Article;
import edu.app.persistence.Event;
import edu.app.persistence.Speaker;
import edu.app.persistence.User;

////////URL http://localhost:2000/Jug-Web/rest/events
@ManagedBean
@Path("/rest")
public class RestService {
	@EJB
	private EventServiceLocal eventServiceLocal;

	@EJB
	private UserServiceLocal userServiceLocal;

	@EJB
	private ArticleServiceLocal articleServiceLocal;

	// Method
	// All Events
	// http://localhost:2000/Jug-Web/rest/AllEvents
	@Path("AllEvents")
	@GET
	@Produces("application/json")
	public List<Event> getAllEvents() {

		return eventServiceLocal.findAllEventJUG();
	}

	// Liste de Speakers

	// http://localhost:2000/Jug-Web/rest/speakers
	@Path("speakers")
	@GET
	@Produces("application/json")
	public List<Speaker> getAllSpeakers() {

		return userServiceLocal.findAllSpeakers();

	}

	// liste des articles
	// http://localhost:2000/Jug-Web/rest/AllArticlesLeader
	@Path("AllArticlesLeader")
	@GET
	@Produces("application/json")
	public List<Article> getAllArtilcesLeader() {

		return articleServiceLocal.findArticleByJUGLeader(0, 4);
	}

	// http://localhost:2000/Jug-Web/rest/AllArticlesFormMember/11
	// list articles from Member
	@Path("AllArticlesFormMember/{id}")
	@GET
	@Produces("application/json")
	public List<Article> getAllArtilcesMember(
			@PathParam(value = "id") int idUser) {
		User user = userServiceLocal.findUserById(idUser);
		return articleServiceLocal.findArticleByMember(user);
	}

	// list articles from Speaker
	// http://localhost:2000/Jug-Web/rest/AllArticlesFormSpeaker
	@Path("AllArticlesFormSpeaker")
	@GET
	@Produces("application/json")
	public List<Article> getAllArtilcesSpeaker() {
		return articleServiceLocal.findarticleBySpaker(0, 5);
	}

	// http://localhost:2000/Jug-Web/rest/AllArticles
	@Path("AllArticles")
	@GET
	@Produces("application/json")
	public List<Article> getAllArtilces() {
		return articleServiceLocal.findAllArticle();
	}

	// http://localhost:2000/Jug-Web/rest/AllArticlesCategorie/java
	@Path("AllArticlesCategorie/{categorie}")
	@GET
	@Produces("application/json")
	public List<Article> getAllArtilcesCategorie(
			@PathParam(value = "categorie") String categorie) {

		return articleServiceLocal.findArticlebycathegorie(0, 2, categorie);
	}

}
