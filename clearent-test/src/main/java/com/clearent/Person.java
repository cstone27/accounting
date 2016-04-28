package com.clearent;

import java.util.HashMap;
import java.util.Map;

import com.clearent.Address.AddressType;

/**
 * A person.
 * 
 * @author Chris Stone
 *
 */
public class Person {
	private Name name;
	private Map<AddressType, Address> addresses = new HashMap<>();
	
	public Person(Name name){
		this.name = name;
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address add(Address address){
		Address oldAddress = null;
		if (address != null){
			oldAddress = addresses.put(address.getType(), address); 
		}
		return oldAddress;
	}
	
	public Address getAddress(AddressType type){
		return addresses.get(type);
	}
}
