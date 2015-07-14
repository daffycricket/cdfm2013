package com.gtanla.android.cloud.bo;

/**
 * @author Nico Represents a geographical coordinate.
 * 
 */
public class GeoCoordinate {

	/**
	 * The altitude.
	 */
	private double altitude;

	/**
	 * The latitude.
	 */
	private double latitude;

	/**
	 * The longitude.
	 */
	private double longitude;

	/**
	 * Constructor.
	 * 
	 * @param altitude
	 * @param latitude
	 * @param longitude
	 */
	public GeoCoordinate(double altitude, double latitude, double longitude) {
		super();
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * GETTER.
	 * 
	 * @return the altitude
	 */
	public double getAltitude() {
		return altitude;
	}

	/**
	 * GETTER.
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * GETTER.
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * SETTER.
	 * 
	 * @param altitude
	 *            the altitude to set
	 */
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	/**
	 * SETTER.
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * SETTER.
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
