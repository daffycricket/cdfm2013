package com.gtanla.android.task;

import android.os.AsyncTask;

import com.gtanla.android.cloud.BackendClient;
import com.gtanla.android.cloud.BackendException;

/**
 * This task is used to retrieve all accounts nearby user from AppEngine database via REST services.
 * 
 * @author gtalbot
 * 
 */
public class GetAccountTask extends AsyncTask<Void, Void, Object> {

	/** Callback. */
	private final IAsyncCallback<Object> callback;

	/** Account identifier. */
	private final Long id;

	/**
	 * Get an account.
	 * 
	 * @param callback
	 *            Method to call after creation completion.
	 * @param id
	 *            Account id.
	 */
	public GetAccountTask(IAsyncCallback<Object> callback, Long id) {
		this.callback = callback;
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Object doInBackground(Void... params) {
		try {
			return BackendClient.getInstance().getAccount(id);
		} catch (BackendException e) {
			return e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Object result) {

		// Call callback
		if (callback != null) {
			callback.execute(result);
		}
	}
}
