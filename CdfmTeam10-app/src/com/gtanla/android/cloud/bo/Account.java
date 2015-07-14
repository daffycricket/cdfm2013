package com.gtanla.android.cloud.bo;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;

/**
 * 
 * @author gtalbot
 * 
 */
public class Account implements Serializable {

	/** UID. */
	private static final long serialVersionUID = 7097914682733397080L;

	/**
	 * User address.
	 */
	private String address;

	/**
	 * City.
	 */
	private String city;

	/**
	 * postal code.
	 */
	private String cp;

	/**
	 * Account creation date
	 */
	private Date creationDate;

	/**
	 * Email.
	 */
	private String email;

	/**
	 * First name.
	 */
	private String firstName;

	/**
	 * Geographical coordinates.
	 */
	private String geoCoordinates;

	/**
	 * Internal id.
	 */
	private Long id;

	/**
	 * Account name
	 */
	private String name;

	/**
	 * Password.
	 */
	private String password;

	/**
	 * Constructor.
	 */
	public Account() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param creationDate
	 * @param email
	 * @param firstName
	 * @param geoCoordinates
	 * @param name
	 * @param password
	 */
	public Account(Date creationDate, String email, String firstName, String geoCoordinates, String name,
			String password) {
		super();
		this.creationDate = creationDate;
		this.email = email;
		this.firstName = firstName;
		this.geoCoordinates = geoCoordinates;
		this.name = name;
		this.password = password;
	}

	/**
	 * Constructor.
	 * 
	 * @param address
	 * @param city
	 * @param cp
	 * @param email
	 * @param firstName
	 * @param geoCoordinates
	 * @param name
	 * @param password
	 */
	public Account(String address, String city, String cp, String email, String firstName, String geoCoordinates,
			String name, String password) {
		super();
		this.address = address;
		this.city = city;
		this.cp = cp;
		this.email = email;
		this.firstName = firstName;
		this.geoCoordinates = geoCoordinates;
		this.name = name;
		this.password = password;
	}

	/**
	 * GETTER.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * GETTER.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * GETTER.
	 * 
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}

	/**
	 * GETTER.
	 * 
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * GETTER.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * GETTER.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * GETTER.
	 * 
	 * @return the geoCoordinates
	 */
	public String getGeoCoordinates() {
		return geoCoordinates;
	}

	/**
	 * GETTER.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the latitude.
	 * 
	 * @return the latitude.
	 */
	public double getLatitude() {
		double toReturn = 0.0;
		if (getGeoCoordinates() != null) {
			GeoCoordinate coordinate = new Gson().fromJson(getGeoCoordinates(), GeoCoordinate.class);
			toReturn = coordinate.getLatitude();
		}
		return toReturn;
	}

	/**
	 * Returns the longitude.
	 * 
	 * @return the longitude.
	 */
	public double getLongitude() {
		double toReturn = 0.0;
		if (getGeoCoordinates() != null) {
			GeoCoordinate coordinate = new Gson().fromJson(getGeoCoordinates(), GeoCoordinate.class);
			toReturn = coordinate.getLongitude();
		}
		return toReturn;
	}

	/**
	 * GETTER.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * GETTER.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * SETTER.
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * SETTER.
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * SETTER.
	 * 
	 * @param cp
	 *            the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
	 * SETTER.
	 * 
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * SETTER.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * SETTER.
	 * 
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * SETTER.
	 * 
	 * @param geoCoordinates
	 *            the geoCoordinates to set
	 */
	public void setGeoCoordinates(String geoCoordinates) {
		this.geoCoordinates = geoCoordinates;
	}

	/**
	 * SETTER.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * SETTER.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * SETTER.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [address=" + address + ", city=" + city + ", cp=" + cp + ", creationDate=" + creationDate
				+ ", email=" + email + ", firstName=" + firstName + ", geoCoordinates=" + geoCoordinates + ", id=" + id
				+ ", name=" + name + ", password=" + password + "]";
	}

}
