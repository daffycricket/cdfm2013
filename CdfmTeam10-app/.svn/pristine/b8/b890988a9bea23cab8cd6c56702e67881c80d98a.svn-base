package com.gtanla.android.app;

import java.util.Collection;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

import com.google.analytics.tracking.android.Log;
import com.gtanla.android.R;
import com.gtanla.android.cloud.BackendClient;
import com.gtanla.android.cloud.bo.Account;
import com.gtanla.android.task.GetAccountTask;
import com.gtanla.android.task.IAsyncCallback;
import com.gtanla.android.utils.ConnexionUtils;

@ReportsCrashes(formKey = "", mailTo = "laurent.nicolas@gmail.com", customReportContent = {
		ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.BRAND, ReportField.PHONE_MODEL,
		ReportField.ANDROID_VERSION, ReportField.SHARED_PREFERENCES, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE,
		ReportField.LOGCAT }, mode = ReportingInteractionMode.DIALOG, resToastText = R.string.crash_toast_text, resDialogText = R.string.crash_dialog_text, resDialogIcon = android.R.drawable.ic_dialog_info, resDialogTitle = R.string.crash_dialog_title, resDialogOkToast = R.string.crash_dialog_ok_toast)
public class App extends Application {

	/**
	 * Singleton.
	 */
	private static App app;

	/**
	 * Returns the singleton.
	 * 
	 * @return
	 */
	public static App getInstance() {
		return app;
	}

	/**
	 * Context logged in account.
	 */
	private Account account;

	/**
	 * Context accounts.
	 */
	private Collection<Account> accounts;

	/**
	 * Callback for account retrieval.
	 */
	private final IAsyncCallback<Object> onAccountRetrievedCallback = new IAsyncCallback<Object>() {

		@Override
		public void execute(Object result) {
			onAccountRetrieved(result);
		}
	};

	/**
	 * GETTER.
	 * 
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Returns the context accounts.
	 * 
	 * @return the context accounts.
	 */
	public Collection<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Returns the context accounts as array.
	 * 
	 * @return the context accounts as array.
	 */
	public Account[] getAccountsAsArray() {
		return accounts.toArray(new Account[accounts.size()]);
	}

	/**
	 * Called when account is retrieved.
	 * 
	 * @param result
	 */
	private void onAccountRetrieved(Object result) {
		// error occured
		if (result instanceof Exception) {
			Log.e((Exception) result);
			return;
		} else {
			// account retrieved correctly
			this.account = (Account) result;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		ACRA.init(this);
		BackendClient.setBackendUrl(super.getString(R.string.urlAppengine).toString());

		// retrieves account with id if connected
		if (ConnexionUtils.isConnected(this)) {
			GetAccountTask task = new GetAccountTask(onAccountRetrievedCallback, ConnexionUtils.getIdConnected(this));
			task.execute();
		}
	}

	/**
	 * SETTER.
	 * 
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * Sets the context accounts.
	 * 
	 * @param accounts
	 */
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
}
