package com.clearent;

/**
 * A person's name.
 * 
 * @author Chris Stone
 *
 */
public class Name {
	private String givenName;
	private String surname;
	private String middleName;
	private String suffix;
	
	public Name() {
		givenName = null;
		surname = null;
		middleName = null;
		suffix = null;
	}


	public Name(String givenName, String surname, String middleName) {
		super();
		this.givenName = givenName;
		this.surname = surname;
		this.middleName = middleName;
	}
	
	public Name(String givenName, String surname, String middleName, String suffix) {
		super();
		this.givenName = givenName;
		this.surname = surname;
		this.middleName = middleName;
		this.suffix = suffix;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
