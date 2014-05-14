package edu.app.business;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.Picture;
import edu.app.persistence.User;

/**
 * The Interface PictureServiceLocal.
 */
@Local
public interface PictureServiceLocal {
	void createPicture(Picture picture);

	void updatePicture(Picture picture);

	void removePicture(Picture picture);

	List<Picture> findAllPicture();

	/**
	 * Read image old way.
	 * 
	 * @param file
	 *            the file
	 * @return the byte[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public byte[] readImageOldWay(File file) throws IOException;

	Picture findByPictureNom(String nom);

	Picture findPictureByUser(User user);
}
