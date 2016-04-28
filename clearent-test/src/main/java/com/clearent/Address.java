package com.clearent;

/**
 * A street address.
 * 
 * @author Chris Stone
 *
 */
public class Address {
	private int streetNumber;
	private String streetName;
	private String unit;
	private String city;
	private String state;
	private String zipcode;
	private AddressType type = AddressType.NONE;
	
	public enum AddressType{
		SHIPPING,
		BILLING,
		HOME,
		WORK,
		OTHER,
		NONE,
		;
	}

	public AddressType getType(){
		return type;
	}
	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
