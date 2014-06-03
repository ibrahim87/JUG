package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Categorie;

@Remote
public interface GestionCategorieRemote {

	boolean createCategorie(Categorie categorie);

	boolean updateCategorie(Categorie categorie);

	boolean deleteCategorie(Categorie categorie);

	Categorie findCategorieById(int idCategorie);

	List<Categorie> findAllCategorie();

	Categorie findCategorieByName(String name);

}
