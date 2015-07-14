package com.gtanla.android.task;

import android.location.Location;
import android.os.AsyncTask;

import com.gtanla.android.cloud.BackendClient;
import com.gtanla.android.cloud.BackendException;

/**
 * An AsyncTask aimed to signin.
 */
public class SigninTask extends AsyncTask<String, Void, Object> {

	/** Callback */
	private final IAsyncCallback<Object> callback;

	/**
	 * The location identified by geoloc service.
	 */
	protected Location mLocation;

	/**
	 * An AsyncTask aimed to signin.
	 * 
	 * @param callback
	 */
	public SigninTask(IAsyncCallback<Object> callback) {
		super();
		this.callback = callback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Object doInBackground(String... args) {
		String email = args[0];
		String password = args[1];
		Object toReturn = null;

		try {
			toReturn = BackendClient.getInstance().checkAccount(email, password);
		} catch (BackendException e) {
			toReturn = e;
		}

		return toReturn;
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
