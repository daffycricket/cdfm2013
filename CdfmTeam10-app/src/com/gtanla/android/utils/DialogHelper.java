package com.gtanla.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.text.Html;

import com.gtanla.android.R;

/**
 * @author Nico Helper class for dealing with dialogs.
 */
public class DialogHelper {

	/**
	 * Ask for geoloc permission.
	 * 
	 * @param activity
	 * @param listener
	 */
	public static void askForGeolocPermission(Activity activity, OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(activity.getString(R.string.lblAskForGeolocPermissionTitle));
		builder.setMessage(Html.fromHtml(activity.getText(R.string.lblAskForGeolocPermissionMessage).toString()));
		builder.setPositiveButton(activity.getString(R.string.lblBtnOk), listener);
		builder.setNegativeButton(activity.getString(R.string.lblBtnCancel), listener);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.show();
	}

	/**
	 * Ask for geoloc permission.
	 * 
	 * @param activity
	 *            Current activity
	 * @param title
	 *            Dialog title
	 * @param message
	 *            Dialog message
	 * @param listener
	 *            Button listener
	 */
	public static void confirm(Activity activity, String title, String message, OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(activity.getString(R.string.lblBtnOk), listener);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.show();
	}
}
