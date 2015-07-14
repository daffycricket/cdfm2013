package com.gtanla.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import com.gtanla.android.R;
import com.gtanla.android.SigninActivity;

/**
 * Utility class to validate input
 * 
 * @author gtalbot
 * 
 */
public class ConnexionUtils {

	/**
	 * Disconnect the user
	 * 
	 * @param act
	 *            Activity
	 */
	public static void disconnect(Activity act) {

		// Clear preferences
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(act);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.commit();

		// Start connexion activity
		Intent intent = new Intent(act, SigninActivity.class);
		act.startActivity(intent);
		act.finish();
	}

	/**
	 * Returns the user id connected.
	 * 
	 * @param ctx
	 *            the context
	 * 
	 * @return The user id
	 */
	public static long getIdConnected(Context ctx) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.getLong(PreferenceConstants.ACCOUNT_ID, 0L);
	}

	/**
	 * 
	 * Handles the behaviour of the connect/disconnect button.
	 * 
	 * @param act
	 *            Current activity
	 * @param menu
	 *            Menu
	 */
	public static void handleConnectButtons(final Activity act, Menu menu) {

		// if the user is connected
		if (ConnexionUtils.isConnected(act)) {

			// Get button label
			String label = act.getResources().getText(R.string.disconnect).toString();

			// Create the menu item
			MenuItem miDisconnect = menu.add(label);
			miDisconnect.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			miDisconnect.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see android.view.MenuItem.OnMenuItemClickListener#onMenuItemClick(android.view.MenuItem)
				 */
				@Override
				public boolean onMenuItemClick(MenuItem item) {

					// Disconnect user
					ConnexionUtils.disconnect(act);
					return true;
				}
			});
			// user is not connected
		} else {
			// Get button label
			String label = act.getResources().getText(R.string.connect).toString();

			// Create the menu item
			MenuItem miConnect = menu.add(label);
			miConnect.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			miConnect.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see android.view.MenuItem.OnMenuItemClickListener#onMenuItemClick(android.view.MenuItem)
				 */
				@Override
				public boolean onMenuItemClick(MenuItem item) {

					// TODO : check with gta
					Intent intent = new Intent(act, SigninActivity.class);
					act.startActivity(intent);
					return true;
				}
			});
		}
	}

	/**
	 * Check the user is connected.
	 * 
	 * @param ctx
	 *            the context
	 * 
	 * @return The user connexion status
	 */
	public static boolean isConnected(Context ctx) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		return preferences.getLong(PreferenceConstants.ACCOUNT_ID, 0L) != 0;
	}
}
