package com.gtanla.backend.dao;

import java.util.Collections;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.googlecode.objectify.Key;
import com.gtanla.backend.bo.Article;
import com.gtanla.backend.exception.BackendException;

/**
 * Article cache.
 */
public class ArticleCache {

	/** Singleton. */
	private static ArticleCache instance;

	/**
	 * Get current instance
	 * 
	 * @return current instance
	 * @throws BackendException
	 *             Backend exception.
	 */
	public static ArticleCache getInstance() throws BackendException {
		if (instance == null) {
			instance = new ArticleCache();
		}
		return instance;
	}

	/**
	 * Memcache instance.
	 */
	private final Cache cache;

	/**
	 * Private default constructor.
	 */
	private ArticleCache() throws BackendException {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			this.cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * Get entity from cache
	 * 
	 * @param key
	 *            Entity key
	 * @return Entity
	 */
	public Article getFromCache(Key<Article> key) {
		return (Article) this.cache.get(key);
	}

	/**
	 * Load entity from cache
	 * 
	 * @param id
	 *            identifier to load
	 * @return The entity
	 */
	public Article getFromCache(long id) {
		return this.getFromCache(new Key<Article>(Article.class, id));
	}

	/**
	 * Check key is in cache
	 * 
	 * @param key
	 *            Key to check
	 * @return TRUE if the entity is in cache
	 */
	public boolean isInCache(Key<Article> key) {
		return this.cache.containsKey(key);
	}

	/**
	 * Check identifier is in cache
	 * 
	 * @param id
	 *            Identifier to check
	 * @return TRUE if the entity is in cache
	 */
	public boolean isInCache(long id) {
		return this.isInCache(new Key<Article>(Article.class, id));
	}

	/**
	 * Put an article in cache
	 * 
	 * @param article
	 *            Entity to store in cache
	 */
	public void putInCache(Article article) {
		this.cache.put(new Key<Article>(Article.class, article.getId().longValue()), article);
	}
}