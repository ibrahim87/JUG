package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Categorie;

/**
 * Session Bean implementation class GestionCategorie
 */
@Stateless
public class GestionCategorie implements GestionCategorieRemote,
		GestionCategorieLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext(name = "PU")
	private EntityManager em;

	public GestionCategorie() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createCategorie(Categorie categorie) {
		em.persist(categorie);
		return true;
	}

	@Override
	public boolean updateCategorie(Categorie categorie) {
		em.merge(categorie);
		return true;
	}

	@Override
	public boolean deleteCategorie(Categorie categorie) {
		em.remove(em.merge(categorie));
		return true;
	}


	public Categorie findCategorieById(int idCategorie) {
		Categorie categorie = null;
		categorie = em.find(Categorie.class, idCategorie);
		return categorie;
	}

	@SuppressWarnings("unchecked")

	public List<Categorie> findAllCategorie() {
		Query query = em.createQuery("select c from Categorie c ");
		return query.getResultList();

	}

	
	public Categorie findCategorieByName(String name) {
		Categorie categorie = null;
		Query query = em
				.createQuery("select c from Categorie c where c.name=:l");
		query.setParameter("l", name);
		try {
			categorie = (Categorie) query.getSingleResult();

		} catch (Exception e) {
			categorie = null;
		}
		return categorie;
		
		
	}

}
