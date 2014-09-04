package Test;


import javax.ejb.EJB;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import edu.app.business.EventServiceLocal;
import edu.app.persistence.Article;
import edu.app.persistence.Event;

public class ArticleTE {
@EJB
	private EventServiceLocal eventServiceLocal;

	 @Test
     @Category(edu.app.business.ArticleService.class)
     public void AddArtilce() {
		 
       Article article = new Article();
       article.setTitle("aaaa");
     }

	 
	 
	 
	 
	 @Test
     @Category(edu.app.business.ArticleServiceLocal.class)
     public void deleteArtilce() {
		 
       Article article = new Article();
      
     }

	 
	    @Test
	   // @Category(edu.app.business.MessageAnswerService.class)
	    public void AddEvent(){
	    Event e = new Event();
	    e.setTitle("JUG2014");
	    e.setDescription("Cet événement constitue une première en Afrique de la communauté JUG international et tient son importance de la qualité des intervenants qui vont y participer. Nous allons accueillir  des invités et des conférenciers de renommé international.");
	     e.setId(1);
	      
	    }
	 
	 
	 
		
}
