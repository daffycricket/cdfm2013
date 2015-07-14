package com.gtanla.backend.dao;

import java.util.Collections;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.googlecode.objectify.Key;
import com.gtanla.backend.bo.Account;
import com.gtanla.backend.exception.BackendException;

/**
 * Account cache.
 */
public class AccountCache {

	/** Singleton. */
	private static AccountCache instance;

	/**
	 * Get current instance
	 * 
	 * @return current instance
	 * @throws BackendException
	 *             Backend exception.
	 */
	public static AccountCache getInstance() throws BackendException {
		if (instance == null) {
			instance = new AccountCache();
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
	private AccountCache() throws BackendException {
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
	public Account getFromCache(Key<Account> key) {
		return (Account) this.cache.get(key);
	}

	/**
	 * Load entity from cache
	 * 
	 * @param id
	 *            identifier to load
	 * @return The entity
	 */
	public Account getFromCache(long id) {
		return this.getFromCache(new Key<Account>(Account.class, id));
	}

	/**
	 * Check key is in cache
	 * 
	 * @param key
	 *            Key to check
	 * @return TRUE if the entity is in cache
	 */
	public boolean isInCache(Key<Account> key) {
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
		return this.isInCache(new Key<Account>(Account.class, id));
	}

	/**
	 * Put an account in cache
	 * 
	 * @param account
	 *            Entity to store in cache
	 */
	public void putInCache(Account account) {
		this.cache.put(new Key<Account>(Account.class, account.getId().longValue()), account);
		this.cache.put(account.getEmail(), account);
	}
}