package onlinemarket.account;

import java.io.Serializable;

public class Account implements Serializable, Comparable<Account>{
	
	private static final long serialVersionUID = 1L;
	
	protected String name, surname, city;
	protected Address address;
	protected Integer cap;
	protected Email email;
	protected Password password;
	protected Long phoneNumber;
	
	public Account(String name, String surname, Email email, Password password, Long phonenumber, Address address) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phonenumber;
		this.address = address;
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
	
	public Address getAddress() {
		return address;
	}

	public boolean login(Account logger) {
		return equals(logger) && logger.getPassword().equals(password);
	}
	
	public String toString() {
		String s = getName()+ "||" + getSurname() + "||" + getEmail().toString() + "||" + getPassword().toString() + "||" + getPhoneNumber() + "||" + address.toString();
		return s; 
	}
	
	public int compareTo(Account other) {
		int a = (this.phoneNumber.intValue() - other.phoneNumber.intValue());
		return a;
	}
}
