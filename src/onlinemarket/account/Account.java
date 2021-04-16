package onlinemarket.account;

import java.io.Serializable;

public class Account implements Serializable, Comparable<Account>{
	private static final long serialVerisonUID = 1L;
	
	protected String name, surname, city, address;
	protected Integer cap;
	protected Email email;
	protected Password password;
	protected Long phoneNumber;
	
	public Account(String name, String surname, Email email, Password password, Long phonenumber, Integer cap, String city, String address) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phonenumber;
		this.cap = cap;
		this.city = city;
		this.address = address;
		checkAll(name, surname, phonenumber, cap, city, address);

		
	}
	
	private void checkAll(String name, String surname, Long phonenumber, Integer cap, String city, String address) throws IllegalArgumentException{
		if(checkName(name))
			throw new IllegalArgumentException("Name field can be filled only by letters from a to z (low and high case)");
		if(checkSurname(surname))
			throw new IllegalArgumentException("Surname field can be filled only by letters from a to z (low and high case)");
		if(checkPhoneNumber(phonenumber))
			throw new IllegalArgumentException("Phone number field can be filled only by numbers from 0 to 9");
		if(checkCap(cap))
			throw new IllegalArgumentException("CAP field can be filled only by numbers from 0 to 9");
		if(checkCity(city))
			throw new IllegalArgumentException("City field can be filled only by letters from a to z (low and high case)");
		if(checkAddress(address))
			throw new IllegalArgumentException("Address field can be filled only by letters from a to z (low and high case) and numbers from 0 to 9");
	}
	
	private boolean checkAddress(String address) {
		for(int i = 0; i < address.length(); i++) {
			if(!(((char)address.charAt(i) >= 'a' && (char)address.charAt(i) <= 'z') || ((char)address.charAt(i) >= 'A' && (char)address.charAt(i) <= 'Z') || (char)address.charAt(i) == ' ' || ((char)city.charAt(i) >= '0' && (char)city.charAt(i) <= '9')))
				return true;
		}
		
		return false;
	}
	
	private boolean checkCity(String city) {
		for(int i = 0; i < city.length(); i++) {
			if(!((char)city.charAt(i) >= 'a' && (char)city.charAt(i) <= 'z') || ((char)address.charAt(i) >= 'A' && (char)address.charAt(i) <= 'Z'))
				return true;
		}
		
		return false;
	}
	
	private boolean checkCap(Integer cap) {
		String s = cap.toString();
		
		if(s.length() != 5)
			return true;
		
		for(int i = 0; i < s.length(); i++) {
			if(!((char) s.charAt(i) >= '0' && (char) s.charAt(i) <= '9'))
				return true;
		}
		
		return false;
	}
	
	private boolean checkPhoneNumber(Long phonenumber) {
		String s = phonenumber.toString();
		
		for(int i = 0; i < s.length(); i++) {
			if(!((char) s.charAt(i) >= '0' && (char) s.charAt(i) <= '9'))
				return true;
		}
		
		return false;		
	}
	
	private boolean checkSurname(String surname) {
		for(int i = 0; i < surname.length(); i++) {
			if(!((char)surname.charAt(i) >= 'a' && (char)surname.charAt(i) <= 'z') || ((char)address.charAt(i) >= 'A' && (char)address.charAt(i) <= 'Z'))
				return true;
		}
		
		return false;
	}
	
	private boolean checkName(String name) {
		for(int i = 0; i < name.length(); i++) {
			if(!((char) name.charAt(i) >= 'a' && (char)name.charAt(i) <= 'z') || ((char)address.charAt(i) >= 'A' && (char)address.charAt(i) <= 'Z'))
				return true;
		}
		
		return false;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Email getEmail() {
		return email;
	}

	public Password getPassword() {
		return password;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}
	
	public Integer getCap() {
		return cap;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getAddress() {
		return address;
	}

	public boolean login(Account logger) {
		return equals(logger) && logger.getPassword().equals(password);
	}
	
	public String toString() {
		String s = getName()+ "||" + getSurname() + "||" + getEmail().toString() + "||" + getPassword().toString() + "||" + getPhoneNumber() + "||" + cap.toString() + "||" + city + "||" + address;
		return s; 
	}
	
	public int compareTo(Account other) {
		int a = (this.phoneNumber.intValue() - other.phoneNumber.intValue());
		return a;
	}
}
