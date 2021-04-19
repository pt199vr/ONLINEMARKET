package onlinemarket.account;

import java.io.Serializable;

public class Address implements Serializable{
	private static final long serialVersionUID = 5L;
	
	private final String where, city, number;
	private final Integer cap;
	
	public Address(String address, String city, Integer cap) {
		this.cap = cap;
		this.city = city;
		this.number = numberof(address);
		this.where = street(address);
		
	}

	private String street(String address) {
		String ret = "";
		for(int i = 0; i < address.length(); i++) {
			if(((char) address.charAt(i) >= 'a' && (char) address.charAt(i) <= 'z' ) || ((char) address.charAt(i) >= 'A' && (char) address.charAt(i) <= 'Z') || (char) address.charAt(i) == ' ') {
				ret += address.charAt(i);
			}
		}
		
		return ret;
	}
	
	private String numberof(String address) {
		String ret = "";
		for(int i = 0; i < address.length(); i++) {
			if((char) address.charAt(i) >= '0' && (char) address.charAt(i) <= '9') {
				ret += address.charAt(i);
			}
		}
		
		return ret;
	}
	
	public Integer getCap() {
		return cap;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getStreet() {
		return where;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Address) {
			Address tmp = (Address) other;
			
			if(this.where.equals(tmp.getStreet()) && this.city.equals(tmp.getCity()) && this.number.equals(tmp.getNumber()) && this.cap.equals(tmp.getCap()))
				return true;
		}
		
		return false;
	}
	
	public String toString() {
		return String.format("%s %05d %s %s", city, cap, where, number);
	}
	
}

