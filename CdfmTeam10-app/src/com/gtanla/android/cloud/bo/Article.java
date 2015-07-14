package com.gtanla.android.cloud.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Articles
 * 
 * @author gtalbot
 * 
 */
public class Article implements Serializable {

	/** UID. */
	private static final long serialVersionUID = 2334036814631147556L;

	/**
	 * HTML content.
	 */
	private String content;

	/**
	 * Internal id.
	 */
	private Long id;

	/**
	 * Modification date
	 */
	private Date modificationDate;

	/**
	 * Article type.
	 */
	private String type;

	/**
	 * Constructor.
	 */
	public Article() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param type
	 *            Article type
	 * @param content
	 *            Article content
	 */
	public Article(String type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	/**
	 * GETTER.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * GETTER.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * GETTER.
	 * 
	 * @return the modificationDate
	 */
	public Date getModificationDate() {
		return modificationDate;
	}

	/**
	 * GETTER.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * SETTER.
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * SETTER.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * SETTER.
	 * 
	 * @param modificationDate
	 *            the modificationDate to set
	 */
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	/**
	 * SETTER.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Update article from an other article
	 * 
	 * @param account
	 */
	public void updateForModification(Article account) {
		this.setContent(account.getContent());

	}

}
