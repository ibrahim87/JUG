package edu.app.business;

import java.util.List;

import javax.ejb.Remote;

import edu.app.persistence.Page;

@Remote
public interface PageServiceRemote {

	void createArticle(Page page);

	void updateArticle(Page page);

	void deleteArticle(Page page);

	Page findPageById(int idPage);

	List<Page> findAllPage();

	List<Page> findPage(String title);

	List<Page> findByKeywordPage(String keyword);

}
