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
	
	
	List<Article> findAllArticleCustum(int pageIndex, int noOfRecords);
	
	List<Article> findArticleByNameCategorie(String name ,int pageIndex, int noOfRecords );
	
	List<Article> findArticleByJUGLeader(User user);

}
