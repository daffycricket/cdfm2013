package com.gtanla.backend.dao;

import java.util.Collection;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.gtanla.backend.bo.Article;
import com.gtanla.backend.exception.BackendException;

/**
 * Objectify implementation.
 */
public class ArticleDao {

	/**
	 * Singleton.
	 */
	private static ArticleDao instance;

	/**
	 * Register objectify service.
	 */
	static {
		ObjectifyService.register(Article.class);
	}

	/**
	 * Get DAO instance.
	 * 
	 * @return the instance
	 */
	public static ArticleDao getInstance() {
		if (instance == null) {
			instance = new ArticleDao();
		}
		return instance;
	}

	/**
	 * Default constructor.
	 */
	private ArticleDao() {
	}

	/**
	 * Create entity "Article"
	 * 
	 * @param entity
	 *            entity to create
	 * @return The key
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Article create(Article entity) throws BackendException {

		// Begin transaction
		Objectify ofy = ObjectifyService.begin();

		// Set technical data
		entity.setId(null);
		entity.setModificationDate(new Date());

		// Store in DB
		Key<Article> toReturn = ofy.put(entity);

		// Store in cache
		ArticleCache.getInstance().putInCache(entity);

		// Return key
		return ofy.get(toReturn);
	}

	/**
	 * Get an article by the key
	 * 
	 * @param key
	 *            The article key
	 * @return The entity "Article"
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Article get(Key<Article> key) throws BackendException {
		try {
			// Check entity is in cache and return it
			Article toReturn = ArticleCache.getInstance().getFromCache(key);
			if (toReturn != null) {
				return toReturn;
			}

			// If not, get entity from DB and add to cache
			Objectify ofy = ObjectifyService.begin();
			toReturn = ofy.get(key);
			ArticleCache.getInstance().putInCache(toReturn);
			return toReturn;

		} catch (NotFoundException e) {
			// If entity is not in DB, return null
			return null;
		}
	}

	/**
	 * Get by identifier
	 * 
	 * @param id
	 *            Article identifier
	 * @return The entity
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Article get(long id) throws BackendException {
		return this.get(new Key<Article>(Article.class, id));
	}

	/**
	 * Get by email
	 * 
	 * @param type
	 *            Article email
	 * @return The entity
	 */
	public Article getByType(String type) {
		Article toReturn = null;
		try {

			// If not, get entity from DB
			Objectify ofy = ObjectifyService.begin();

			toReturn = ofy.query(Article.class).filter("type", type).get();
		} catch (Exception e) {
			toReturn = null;
		}

		return toReturn;
	}

	/**
	 * List all articles
	 * 
	 * @return All articles
	 */
	public Collection<Article> list() {
		Objectify ofy = ObjectifyService.begin();
		return ofy.query(Article.class).order("-id").list();
	}

	/**
	 * Update the article
	 * 
	 * @param entity
	 *            article to update
	 * @return updated article
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Article update(Article entity) throws BackendException {
		// Begin transaction
		Objectify ofy = ObjectifyService.begin();

		// Store in DB
		Key<Article> toReturn = ofy.put(entity);

		// Store in cache
		ArticleCache.getInstance().putInCache(entity);

		// Return key
		return ofy.get(toReturn);
	}

}