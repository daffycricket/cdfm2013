package com.gtanla.android.cloud;

/**
 * Backend exception.
 * 
 * @author gtalbot
 * 
 */
public class BackendException extends Exception {

	/** UID. */
	private static final long serialVersionUID = 5439192283661316005L;

	/**
	 * Constructor.
	 * 
	 * @param detailMessage
	 */
	public BackendException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * Constructor.
	 * 
	 * @param throwable
	 */
	public BackendException(Throwable throwable) {
		super(throwable);
	}

}
