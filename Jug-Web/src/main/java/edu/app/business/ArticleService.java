package edu.app.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Article;
import edu.app.persistence.Categorie;
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


	@SuppressWarnings("unchecked")
	public List<Article> findAllArticleCustum( String status ,int pageIndex, int noOfRecords) {
	
		Query query = em.createQuery("select ar from Article ar where ar.status='public' ORDER BY ar.idArticle DESC ");
		return query.setMaxResults(noOfRecords)
				.setFirstResult(noOfRecords * pageIndex).getResultList();
	}


	@SuppressWarnings("unchecked")
	public List<Article> findArticleByCategorie( Categorie categorie ,int pageIndex, int noOfRecords) {
		Query query = em
				.createQuery("select ar from Article ar where ar.categorie=:cat");
		query.setParameter("cat", categorie);
		return query.getResultList();
		
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Article> findArticleByJUGLeader(int pageIndex, int noOfRecords) {
		Query query = em
				.createQuery("select ar from Article ar where ar.user.idUser=30");
		//query.setParameter("leader", leader.getidUser());
		return query.getResultList();
		
	}
	 
	@SuppressWarnings("unchecked")
	public List<Article> findArticleJava( int pageIndex, int noOfRecords) {
		
		Query query = em
				.createQuery("select ar from Article ar where ar.status='public' and ar.categorie.name='java' ORDER BY ar.idArticle DESC ");
		
		return query.setMaxResults(noOfRecords)
				.setFirstResult(noOfRecords * pageIndex).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Article> findArticlebycategorie(String name) {

		Categorie categorie =new Categorie();

		 String jpql1 = "select a from Categorie a where a.name=:s";
	        
	        Query query1 = em.createQuery(jpql1);
	        query1.setParameter("s", name);
	        categorie=(Categorie) query1.getSingleResult();
		List<Article> articles=new ArrayList<Article>();
		
		 String jpql = "select a from Artilce a where a.categorie=:s";
		        
		        Query query = em.createQuery(jpql);
		        query.setParameter("s", categorie);
		        articles = query.getResultList();
		        
		       
		        
		        return articles;
		        
		    }

	
	@SuppressWarnings("unchecked")
	public List<Article> findArticleByJEE(int pageIndex, int noOfRecords) {
		
Query query = em.createQuery("select ar from Article ar where ar.status='public' and ar.categorie.name='JEE' ORDER BY ar.idArticle DESC ");
		return query.setMaxResults(noOfRecords)
				.setFirstResult(noOfRecords * pageIndex).getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findOneArticleJava(int pageIndex, int noOfRecords) {
		Query query = em
				.createQuery("select ar from Article ar where ar.status='public' and ar.categorie.name='java'");
		
		return query.setMaxResults(noOfRecords)
				.setFirstResult(noOfRecords * pageIndex).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> findOneOtherArticle(int pageIndex, int noOfRecords) {
		Query query = em
				.createQuery("select ar from Article ar where ar.status='public' and ar.categorie.name='Other'");
		
		return query.setMaxResults(noOfRecords)
				.setFirstResult(noOfRecords * pageIndex).getResultList();
	}

	@SuppressWarnings("unchecked")

	public List<Article> findArticleOther(int pageIndex, int noOfRecords) {
		Query query = em.createQuery("select ar from Article ar where ar.status='public' and ar.categorie.name='Other' ORDER BY ar.idArticle DESC ");
		return query.setMaxResults(noOfRecords)
				.setFirstResult(noOfRecords * pageIndex).getResultList();

		
	}

	@SuppressWarnings("unchecked")

	public List<Article> findarticleBySpaker( int pageIndex, int noOfRecords) {
		Query query = em.createQuery("select ar from Speaker s join s.articles ar wehre ar!=null");
		return query.setMaxResults(noOfRecords)
				.setFirstResult(noOfRecords * pageIndex).getResultList();

	}

	

	

}
