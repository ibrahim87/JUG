package edu.app.business;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class ArticleService
 */
@Stateless
public class ArticleService implements ArticleServiceRemote, ArticleServiceLocal {

    /**
     * Default constructor. 
     */
    public ArticleService() {
        // TODO Auto-generated constructor stub
    }

}
