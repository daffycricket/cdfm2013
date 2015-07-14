package com.gtanla.android;

import java.util.Collection;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gtanla.android.app.App;
import com.gtanla.android.cloud.bo.Account;

/**
 * Fragment for displaying the list of accounts.
 * 
 * @author Nico
 * 
 */
public class TouchListFragment extends ListFragment {

	/**
	 * Custom adapter for list.
	 * 
	 * @author Nico
	 * 
	 */
	protected class CustomAdapter extends ArrayAdapter<Account> {

		/**
		 * Constructeur
		 * 
		 * @param context
		 * @param resource
		 */
		public CustomAdapter(Context context, int resource) {
			super(context, resource, accountsAsArray);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Get convertView
			View v = convertView;
			if (v == null) {
				LayoutInflater vi;
				vi = LayoutInflater.from(getContext());
				v = vi.inflate(R.layout.item_account_list, null);
			}

			// Get data by position
			Account account = accountsAsArray[position];

			// If data present, create view
			if (account != null) {

				// TextView for "name + firstname"
				TextView txtName = (TextView) v.findViewById(R.id.txtName);
				if (txtName != null) {
					txtName.setText(account.getName() + " " + account.getFirstName());
				}

				// TextView for "address"
				TextView txtAddress = (TextView) v.findViewById(R.id.txtAddress);
				if (txtAddress != null) {
					txtAddress.setText(account.getAddress());
				}

				if (position % 2 == 0) {
					v.setBackgroundColor(Color.GRAY);
				} else {
					v.setBackgroundColor(Color.WHITE);
				}
			}
			return v;
		}
	}

	/**
	 * Creates and returns a new TouchFilterFragment.
	 * 
	 * @param accounts
	 * @return a new TouchFilterFragment.
	 */
	public static TouchListFragment newInstance() {
		TouchListFragment fragment = new TouchListFragment();

		Bundle args = new Bundle();
		fragment.setArguments(args);

		return fragment;
	}

	private Account[] accountsAsArray;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Collection<Account> accounts = App.getInstance().getAccounts();
		accountsAsArray = accounts.toArray(new Account[accounts.size()]);

		setListAdapter(new CustomAdapter(this.getActivity(), R.layout.item_account_list));
		//
		// String[] items = new String[App.getInstance().getAccounts().size()];
		// int i = 0;
		// for (Account item : App.getInstance().getAccounts()) {
		// items[i] = item.getEmail();
		// ++i;
		// }
		//
		// ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
		// items);
		// setListAdapter(aa);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup,
	 * android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_touch_list, container, false);
	}

}
