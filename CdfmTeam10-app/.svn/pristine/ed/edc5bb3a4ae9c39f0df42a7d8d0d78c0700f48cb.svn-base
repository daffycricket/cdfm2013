package com.gtanla.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gtanla.android.app.App;
import com.gtanla.android.cloud.bo.Account;
import com.gtanla.android.utils.Constants;

/**
 * Handles touch map.
 * 
 * @author Nico
 * 
 */
public class TouchMapFragment extends Fragment {

	/**
	 * Zoom level CLOSE.
	 */
	private static final int ZOOM_LEVEL_CLOSE = 11;

	/**
	 * Zoom level HIGH.
	 */
	private static final int ZOOM_LEVEL_HIGH = 5;

	/**
	 * Creates and returns a new TouchFilterFragment.
	 * 
	 * @return a new TouchFilterFragment.
	 */
	public static TouchMapFragment newInstance() {
		TouchMapFragment fragment = new TouchMapFragment();

		Bundle args = new Bundle();
		// args.putInt(ActivityParams.PARAM_GAME_INDEX, gameIndex);
		fragment.setArguments(args);

		return fragment;
	}

	/**
	 * The map object.
	 */
	private GoogleMap mGoogleMap;

	/**
	 * The view.
	 */
	private MapView mMapView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		for (Account item : App.getInstance().getAccounts()) {
			if (item.getGeoCoordinates() != null && item.getGeoCoordinates().length() > 0) {
				// GeoCoordinate geoCoordinate = gson.fromJson(item.getGeoCoordinates(), GeoCoordinate.class);
				mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(item.getLatitude(), item.getLongitude()))
						.title(item.getEmail()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup,
	 * android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_mapview, container, false);

		// Gets the MapView from the XML layout and creates it
		mMapView = (MapView) v.findViewById(R.id.mapview);
		mMapView.onCreate(savedInstanceState);
		// http: // marketplace.eclipse.org/marketplace-client-intro?mpc_install=1336
		// Gets to GoogleMap from the MapView and does initialization stuff
		mGoogleMap = mMapView.getMap();
		mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
		// map.setMyLocationEnabled(true);

		// Needs to call MapsInitializer before doing any CameraUpdateFactory calls
		try {
			MapsInitializer.initialize(this.getActivity());
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		}

		// find params
		int zoomLevel;
		Intent intent = getActivity().getIntent();
		String viewType = (String) intent.getExtras().get(Constants.PARAM_VIEW_TYPE);
		if (viewType == null || viewType.equals(Constants.VIEW_TYPE_AROUND)) {
			zoomLevel = ZOOM_LEVEL_CLOSE;
		} else {
			zoomLevel = ZOOM_LEVEL_HIGH;
		}

		// Updates the location and zoom of the MapView
		Account account = App.getInstance().getAccount();
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
				new LatLng(account.getLatitude(), account.getLongitude()), zoomLevel);
		mGoogleMap.animateCamera(cameraUpdate);

		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onLowMemory()
	 */
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		mMapView.onResume();
		super.onResume();
	}

}