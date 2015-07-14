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
public class GetAccountsTask extends AsyncTask<Void, Void, Object> {

	/** Callback. */
	private final IAsyncCallback<Object> callback;

	/**
	 * Get an account.
	 * 
	 * @param callback
	 *            Method to call after background process completion.
	 */
	public GetAccountsTask(IAsyncCallback<Object> callback) {
		this.callback = callback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Object doInBackground(Void... params) {
		try {
			// should use accountCoordinates but...
			return BackendClient.getInstance().listAccounts(/* accountCoordinates */);
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
