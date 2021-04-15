package onlinemarket.account;

import java.io.Serializable;

public class Account implements Serializable, Comparable<Account>{
	private static final long serialVerisonUID = 1L;
	
	protected String name, surname, city, address;
	protected Integer cap;
	protected Email email;
	protected Password password;
	protected Long phoneNumber;
	
	public Account(String name, String surname, Email email, Password password, Long phoneNumber, Integer cap, String city, String address) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		if(checkCap(cap))
			this.cap = cap;
		this.city = city;
		this.address = address;
		
	}
	
	private boolean checkCap(Integer cap) throws IllegalArgumentException{
		String s = cap.toString();
		if(s.length() != 5) {
			throw new IllegalArgumentException("CAP is made up of 5 numbers");
		}
		return true;
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
