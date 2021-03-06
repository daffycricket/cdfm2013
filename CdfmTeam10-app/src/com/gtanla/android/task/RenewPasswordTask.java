package com.gtanla.android.task;

import android.os.AsyncTask;

import com.gtanla.android.cloud.BackendClient;
import com.gtanla.android.cloud.BackendException;

/**
 * This task is used to renew an email via REST services.
 * 
 * @author gtalbot
 * 
 */
public class RenewPasswordTask extends AsyncTask<String, Void, Void> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Void doInBackground(String... email) {
		try {
			BackendClient.getInstance().renewPassword(email[0]);
		} catch (BackendException e) {
			;
		}

		return null;
	}
}
