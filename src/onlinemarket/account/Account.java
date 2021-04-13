package onlinemarket.account;

import java.io.Serializable;

public abstract class Account implements Serializable, Comparable<Account>{
	private static final long serialVerisonUID = 1L;
	
	protected String name, surname;
	protected Email email;
	protected Password password;
	protected Long phoneNumber;
	
	public Account(String name, String surname, Email email, Password password, Long phoneNumber) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
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

	public boolean login(Account logger) {
		return equals(logger) && logger.getPassword().equals(password);
	}
	
	public abstract boolean equals(Object other);
	public abstract String toString();
	public abstract int compareTo(Account other);
	
}
