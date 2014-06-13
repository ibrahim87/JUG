package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Article;
import edu.app.persistence.Categorie;
import edu.app.persistence.Leader;
import edu.app.persistence.User;



@Remote
public interface ArticleServiceRemote {
	
	
	void createArticle(Article article);
	void updateArticle(Article article);
	void deleteArticle(Article article);
	
	
	Article findArticleById(int idArticle);

	List<Article> findAllArticle();
	List<Article> findArticle(String title);
	
	List<Article> findByKeywordArticle(String keyword);
	
	List<Article> findArticleByMember(User user);
	
	List<Article> findAllArticleCustum(  String status, int pageIndex, int noOfRecords);
	
	 List<Article> findArticleJava(  int pageIndex, int noOfRecords);
	
		
	 List<Article> findArticleByJUGLeader(int pageIndex, int noOfRecords);
	

}
