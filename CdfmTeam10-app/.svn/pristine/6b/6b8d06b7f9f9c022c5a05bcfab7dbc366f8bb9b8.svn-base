package com.gtanla.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.gtanla.android.task.RenewPasswordTask;
import com.gtanla.android.utils.ValidationUtils;

/**
 * Handles user signin case.
 * 
 * @author Nico
 * 
 */
public class NewPasswordActivity extends Activity {

	/**
	 * The email edit text.
	 */
	private EditText edtEmail;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_password);

		// track
		EasyTracker.getInstance(this).send(
				MapBuilder.createAppView().set(Fields.SCREEN_NAME, "NewPasswordScreen").build());

		// Enable HOME button
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		edtEmail = (EditText) this.findViewById(R.id.edtEmail);

		// Click sur Register
		Button btnSendNewMail = (Button) this.findViewById(R.id.btnSendNewMail);
		btnSendNewMail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// track
				EasyTracker.getInstance(NewPasswordActivity.this).send(
						MapBuilder.createAppView().set(Fields.EVENT_ACTION, "NewPasswordScreen_REGISTER").build());

				String errorMessageEmailPattern = NewPasswordActivity.this.getResources()
						.getText(R.string.errorMessageEmailPattern).toString();
				boolean validity = ValidationUtils.validateEditTextIsEmail(edtEmail, errorMessageEmailPattern);

				if (validity) {
					new RenewPasswordTask().execute(edtEmail.getText().toString());

					// display info and quit
					String infoRenewEmailMessage = NewPasswordActivity.this.getResources()
							.getText(R.string.infoRenewEmailMessage).toString();
					Toast.makeText(NewPasswordActivity.this, infoRenewEmailMessage, Toast.LENGTH_LONG).show();
					NewPasswordActivity.this.finish();
				}
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
		EasyTracker.getInstance(this).activityStart(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}
}