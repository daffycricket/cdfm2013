package com.gtanla.android.task;

/**
 * Callback for all activity
 * 
 * @author gtalbot
 * 
 * @param <T>
 */
public interface IAsyncCallback<T> {

	/**
	 * Executes the callback.
	 * 
	 * @param object
	 *            execution parameter
	 */
	void execute(T object);
}
