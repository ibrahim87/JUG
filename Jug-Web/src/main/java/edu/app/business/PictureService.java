package edu.app.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.app.persistence.Picture;
import edu.app.persistence.User;

// TODO: Auto-generated Javadoc
/**
 * Session Bean implementation class PictureService.
 */
@Stateless
public class PictureService implements PictureServiceRemote,
		PictureServiceLocal {

	/** The em. */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public PictureService() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see virtualUniversity.business.PictureServiceRemote#createPicture(
	 * virtualUniversity.persistenceAccount.Picture)
	 */
	public void createPicture(Picture picture) {
		em.persist(picture);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see virtualUniversity.business.PictureServiceRemote#updatePicture(
	 * virtualUniversity.persistenceAccount.Picture)
	 */
	public void updatePicture(Picture picture) {
		em.merge(picture);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see virtualUniversity.business.PictureServiceRemote#removePicture(
	 * virtualUniversity.persistenceAccount.Picture)
	 */
	public void removePicture(Picture picture) {
		em.remove(em.merge(picture));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see virtualUniversity.business.PictureServiceRemote#findAllPicture()
	 */
	@SuppressWarnings("unchecked")
	public List<Picture> findAllPicture() {
		return em.createQuery("select p from Picture p").getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * virtualUniversity.business.PictureServiceRemote#readImageOldWay(java.
	 * io.File)
	 */
	@SuppressWarnings("resource")
	public byte[] readImageOldWay(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			System.out.println("File too large");
		}

		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		is.close();
		return bytes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * virtualUniversity.business.PictureServiceRemote#findByPictureNom(java
	 * .lang.String)
	 */
	public Picture findByPictureNom(String name) {
		Picture p = null;

		Query query = em.createQuery(
				"select p from Picture p where p.pictureName=:z").setParameter(
				"z", name);
		Object obj = null;
		try {
			obj = query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		if (obj != null)
			p = (Picture) obj;
		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see virtualUniversity.business.PictureServiceRemote#findPictureByUser(
	 * virtualUniversity.persistenceAccount.User)
	 */
	public Picture findPictureByUser(User user) {
		Picture p = null;

		Query query = em.createQuery("select p from Picture p where p.user=:z")
				.setParameter("z", user);
		Object obj = null;
		try {
			obj = query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		if (obj != null)
			p = (Picture) obj;
		return p;
	}

}
