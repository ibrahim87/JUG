package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Page;

/**
 * Session Bean implementation class PageService
 */
@Stateless
public class PageService implements PageServiceRemote, PageServiceLocal {

	@PersistenceContext(name = "PU")
	private EntityManager em;

	public PageService() {

	}

	public void createArticle(Page page) {
		em.persist(page);

	}

	public void updateArticle(Page page) {
		em.merge(page);

	}

	public void deleteArticle(Page page) {
		em.remove(em.merge(page));
	}

	public Page findPageById(int idPage) {
		Page page = null;
		page = em.find(Page.class, idPage);
		return page;
	}

	@SuppressWarnings("unchecked")
	public List<Page> findAllPage() {
		Query query = em.createQuery("select p from Page p");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Page> findPage(String title) {
		Query query = em
				.createQuery("select p from Page p where p.title LIKE :title");
		query.setParameter("title", title);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Page> findByKeywordPage(String keyword) {
		List<Page> toFind = null;
		Query query = em
				.createQuery("select p from Page p where UPPER(p.titre) like:param");
		query.setParameter("param", "%" + keyword.toUpperCase() + "%");
		toFind = query.getResultList();
		return toFind;
	}

}
