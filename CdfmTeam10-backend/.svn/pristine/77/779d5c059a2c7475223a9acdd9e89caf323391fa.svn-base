package com.gtanla.backend.dao;

import java.util.Collection;
import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.gtanla.backend.bo.Account;
import com.gtanla.backend.exception.BackendException;

/**
 * Objectify implementation.
 */
public class AccountDao {

	/**
	 * Singleton.
	 */
	private static AccountDao instance;

	/**
	 * Register objectify service.
	 */
	static {
		ObjectifyService.register(Account.class);
	}

	/**
	 * Get DAO instance.
	 * 
	 * @return the instance
	 */
	public static AccountDao getInstance() {
		if (instance == null) {
			instance = new AccountDao();
		}
		return instance;
	}

	/**
	 * Default constructor.
	 */
	private AccountDao() {
	}

	/**
	 * Create entity "Account"
	 * 
	 * @param entity
	 *            entity to create
	 * @return The key
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Account create(Account entity) throws BackendException {

		// Begin transaction
		Objectify ofy = ObjectifyService.begin();

		// Set technical data
		entity.setId(null);
		entity.setCreationDate(new Date());

		// Store in DB
		Key<Account> toReturn = ofy.put(entity);

		// Store in cache
		AccountCache.getInstance().putInCache(entity);

		// Return key
		return ofy.get(toReturn);
	}

	/**
	 * Get an account by the key
	 * 
	 * @param key
	 *            The account key
	 * @return The entity "Account"
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Account get(Key<Account> key) throws BackendException {
		try {
			// Check entity is in cache and return it
			Account toReturn = AccountCache.getInstance().getFromCache(key);
			if (toReturn != null) {
				return toReturn;
			}

			// If not, get entity from DB and add to cache
			Objectify ofy = ObjectifyService.begin();
			toReturn = ofy.get(key);
			AccountCache.getInstance().putInCache(toReturn);
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
	 *            Account identifier
	 * @return The entity
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Account get(long id) throws BackendException {
		return this.get(new Key<Account>(Account.class, id));
	}

	/**
	 * Get by email
	 * 
	 * @param email
	 *            Account email
	 * @return The entity
	 */
	public Account getByEmail(String email) {
		Account toReturn = null;
		try {
			// TODO cache management
			// Check entity is in cache and return it
			// Account toReturn = AccountCache.getInstance().getFromCache(key);
			// if (toReturn != null) {
			// return toReturn;
			// }

			// If not, get entity from DB
			Objectify ofy = ObjectifyService.begin();

			toReturn = ofy.query(Account.class).filter("email", email).get();
		} catch (Exception e) {
			toReturn = null;
		}

		return toReturn;
	}

	/**
	 * List all accounts
	 * 
	 * @return All accounts
	 */
	public Collection<Account> list() {
		Objectify ofy = ObjectifyService.begin();
		return ofy.query(Account.class).order("-id").list();
	}

	/**
	 * Update the account
	 * 
	 * @param entity
	 *            account to update
	 * @return updated account
	 * @throws BackendException
	 *             Persistence exception
	 */
	public Account update(Account entity) throws BackendException {
		// Begin transaction
		Objectify ofy = ObjectifyService.begin();

		// Store in DB
		Key<Account> toReturn = ofy.put(entity);

		// Store in cache
		AccountCache.getInstance().putInCache(entity);

		// Return key
		return ofy.get(toReturn);
	}

}