package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Article;
import edu.app.persistence.User;

@Local
public interface ArticleServiceLocal {

	void createArticle(Article article);

	void updateArticle(Article article);

	void deleteArticle(Article article);

	Article findArticleById(int idArticle);

	List<Article> findAllArticle();

	List<Article> findArticle(String title);

	List<Article> findByKeywordArticle(String keyword);

	List<Article> findArticleByMember(User user);
	
	
	
	List<Article> findAllArticleCustum( String status ,int pageIndex, int noOfRecords);
	
	 List<Article> findArticleJava(  int pageIndex, int noOfRecords);
	
	 List<Article> findArticleByJUGLeader(int pageIndex, int noOfRecords);
	 
	 List<Article> findArticleByJEE(  int pageIndex, int noOfRecords);
	 
	
	 public List<Article> findArticlebycategorie(String name);
	 
	 List<Article> findOneArticleJava(  int pageIndex, int noOfRecords);
	 
	 List<Article> findOneOtherArticle(  int pageIndex, int noOfRecords);
	 
	 List<Article> findArticleOther(  int pageIndex, int noOfRecords);
	 
	 List<Article> findarticleBySpaker( int pageIndex, int noOfRecords);
	 
	 
}
