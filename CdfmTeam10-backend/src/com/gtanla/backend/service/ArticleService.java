package com.gtanla.backend.service;

import java.util.Collection;
import java.util.logging.Logger;

import com.gtanla.backend.bo.Article;
import com.gtanla.backend.dao.ArticleDao;
import com.gtanla.backend.exception.BackendException;

/**
 * All article services.
 * 
 * @author gtalbot
 * 
 */
public class ArticleService {

	/** Error message when the object already exists. */
	private static final String ERROR_MESSAGE_EXISTING_OBJECT = "Object already exists";

	/** Error message when the object has no mail. */
	private static final String ERROR_MESSAGE_NO_TYPE = "Type is needed";

	/** Error message when the object do not exists. */
	private static final String ERROR_MESSAGE_NOT_EXISTING_OBJECT = "Object do not exists";

	/** Logger. */
	public static final Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** Email min length. */
	private static final int TYPE_MIN_LENGTH = 4;

	/**
	 * Create an article.
	 * 
	 * @param article
	 *            An article to create
	 * @return the new article
	 * @throws BackendException
	 *             backend exception
	 */
	public static Article create(Article article) throws BackendException {

		// Check the object already exists
		Article existingArticle = ArticleService.getByType(article.getType());
		if (existingArticle != null) {
			throw new BackendException(ERROR_MESSAGE_EXISTING_OBJECT);
		}

		// Check rules
		if (article.getType() == null || article.getType().length() < TYPE_MIN_LENGTH) {
			throw new BackendException(ERROR_MESSAGE_NO_TYPE);
		}

		Article res = ArticleDao.getInstance().create(article);
		return res;
	}

	/**
	 * Create some articles.
	 * 
	 * @param articles
	 *            Some articles to create
	 * @throws BackendException
	 *             backend exception
	 */
	public static void create(Collection<Article> articles) throws BackendException {
		if (articles != null) {
			for (Article article : articles) {
				create(article);
			}
		}
	}

	/**
	 * Retrieve an article by its ID.
	 * 
	 * @param id
	 *            Article identifier
	 * @return The article instance.
	 * @throws BackendException
	 *             backend exception
	 */
	public static Article get(Long id) throws BackendException {
		ArticleDao dao = ArticleDao.getInstance();
		return dao.get(id);
	}

	/**
	 * Retrieve an article by its email.
	 * 
	 * @param email
	 *            Article email.
	 * @return The article instance.
	 * @throws BackendException
	 *             backend exception
	 */
	public static Article getByType(String email) throws BackendException {
		ArticleDao dao = ArticleDao.getInstance();
		return dao.getByType(email);
	}

	/**
	 * List all articles
	 * 
	 * @return All stored articles
	 * @throws BackendException
	 *             backend exception
	 */
	public static Collection<Article> listArticles() throws BackendException {
		ArticleDao dao = ArticleDao.getInstance();
		return dao.list();
	}

	/**
	 * Update an article.
	 * 
	 * @param article
	 *            Data to store
	 * @return The updated data
	 * @throws BackendException
	 *             Exception
	 */
	public static Article update(Article article) throws BackendException {

		// Check the article exists
		Article storedArticle = ArticleService.get(article.getId());
		if (storedArticle == null) {
			throw new BackendException(ERROR_MESSAGE_NOT_EXISTING_OBJECT);
		}

		// Update fields
		storedArticle.updateForModification(article);

		// Store modifications
		ArticleDao dao = ArticleDao.getInstance();
		storedArticle = dao.update(storedArticle);

		// return object
		return storedArticle;
	}
}
