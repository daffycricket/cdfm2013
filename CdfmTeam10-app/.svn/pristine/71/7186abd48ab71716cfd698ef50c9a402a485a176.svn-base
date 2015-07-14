package com.gtanla.android.task;

import android.os.AsyncTask;

import com.gtanla.android.cloud.BackendClient;
import com.gtanla.android.cloud.BackendException;
import com.gtanla.android.cloud.bo.Account;

/**
 * This task is used to create a ACCOUNT instance in AppEngine database via REST services.
 * 
 * @author gtalbot
 * 
 */
public class CreateAccountTask extends AsyncTask<Void, Void, Object> {

	/** Account to create. */
	private final Account accountToCreate;

	/** Callback. */
	private final IAsyncCallback<Object> callback;

	/**
	 * Create a CreateAccountTask.
	 * 
	 * @param callback
	 *            Method to call after creation completion.
	 * @param accountToCreate
	 *            Account to create.
	 */
	public CreateAccountTask(IAsyncCallback<Object> callback, Account accountToCreate) {
		this.callback = callback;
		this.accountToCreate = accountToCreate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Object doInBackground(Void... params) {
		try {
			return BackendClient.getInstance().createAccount(accountToCreate);
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
