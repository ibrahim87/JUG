package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Article;
import edu.app.persistence.User;


@Stateless
public class ArticleService implements ArticleServiceRemote, ArticleServiceLocal {

	@PersistenceContext(name="PU")
	private EntityManager em;
	
    public ArticleService() {
    }

	public void createArticle(Article article) {
		em.persist(article);
		
	}

	public void updateArticle(Article article) {
		em.merge(article);
		
	}

	public void deleteArticle(Article article) {
		em.remove(em.merge(article));
		
	}

	public Article findArticleById(int idArticle) {
		Article article = null;
		article = em.find(Article.class, idArticle);
		return article;
	}

	@SuppressWarnings("unchecked")
	public List<Article> findAllArticle() {
		Query query = em.createQuery("select a from Article a");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Article> findArticle(String title) {
		Query query = em
				.createQuery("select m from Article a where a.title LIKE :title");
		query.setParameter("title", title);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Article> findByKeywordArticle(String keyword) {
		List<Article> toFind = null;
		Query query = em
				.createQuery("select a from Article a where UPPER(c.title) like:param");
		query.setParameter("param", "%" + keyword.toUpperCase() + "%");
		toFind = query.getResultList();
		return toFind;
	}

	@SuppressWarnings("unchecked")
	public List<Article> findArticleByMember(User user) {
	
		Query query = em
				.createQuery("select a from Article a where a.member.id=:member");
		query.setParameter("member", user.getidUser());
		return query.getResultList();
	}

	

}
