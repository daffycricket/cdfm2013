package com.gtanla.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.gtanla.android.utils.ConnexionUtils;

/**
 * Handles the splash screen.
 * 
 * @author Nico
 * 
 */
public class SplashActivity extends Activity {

	/** Timer */
	private static final int SPLASH_TIME_OUT = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// track
		EasyTracker.getInstance(this).send(MapBuilder.createAppView().set(Fields.SCREEN_NAME, "SplashScreen").build());

		// timer for image
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				onSplashScreenFinished();
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Called when splash is over.
	 */
	private void onSplashScreenFinished() {

		Intent intent = null;
		// session persisted
		if (!ConnexionUtils.isConnected(this)) {
			intent = new Intent(SplashActivity.this, SigninActivity.class);
			// no session
		} else {
			intent = new Intent(SplashActivity.this, MainDashboardActivity.class);
		}

		startActivity(intent);
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

}
