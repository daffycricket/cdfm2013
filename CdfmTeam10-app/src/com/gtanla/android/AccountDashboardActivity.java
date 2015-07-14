package com.gtanla.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.gtanla.android.utils.ConnexionUtils;

/**
 * This activity is page C.
 * 
 * @author gtalbot
 * 
 */
public class AccountDashboardActivity extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_dashboard);

		// track
		EasyTracker.getInstance(this).send(
				MapBuilder.createAppView().set(Fields.SCREEN_NAME, "AccountDashboard").build());

		// Enable HOME button
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		// Add listener on MYACCOUNT button
		Button btnMyAccount = (Button) this.findViewById(R.id.btnMyAccount);
		btnMyAccount.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {

				// track
				EasyTracker.getInstance(AccountDashboardActivity.this).send(
						MapBuilder.createAppView().set(Fields.EVENT_ACTION, "AccountDashboard_MYACCOUNT").build());

				Intent intent = new Intent(AccountDashboardActivity.this, AccountDetailActivity.class);
				AccountDashboardActivity.this.startActivity(intent);
			}
		});

		// Add listener on DISCONNECT button
		Button btnDisconnect = (Button) this.findViewById(R.id.btnDisconnect);
		btnDisconnect.setOnClickListener(new OnClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			@Override
			public void onClick(View v) {
				// track
				EasyTracker.getInstance(AccountDashboardActivity.this).send(
						MapBuilder.createAppView().set(Fields.EVENT_ACTION, "AccountDashboard_DISCONNECT").build());

				ConnexionUtils.disconnect(AccountDashboardActivity.this);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	public void onStart() {
		super.onStart();

		// Notify Google analytics tracker
		EasyTracker.getInstance(this).activityStart(this);

		// Get connexion status
		boolean connected = ConnexionUtils.isConnected(this);

		// Update buttons
		((Button) this.findViewById(R.id.btnMyAccount)).setEnabled(connected);
		((Button) this.findViewById(R.id.btnDisconnect)).setEnabled(connected);
		((Button) this.findViewById(R.id.btnMyParams)).setEnabled(connected);
		((Button) this.findViewById(R.id.btnMyEvents)).setEnabled(connected);
		((Button) this.findViewById(R.id.btnMyFavorites)).setEnabled(connected);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	public void onStop() {
		super.onStop();

		// Notify Google analytics tracker
		EasyTracker.getInstance(this).activityStop(this);
	}
}