package onlinemarket.person;

import java.io.Serializable;

import onlinemarket.employee.EmployeeGui;

public abstract class Person implements Comparable<Person>, Serializable{
	private static final long serialVersionUID = 4L;
	protected String name, surname;
	protected Email email;
	protected Password password;
	protected long phonenumber;
	protected transient PersonGui gui;
	
	public abstract void setGui();
	
	
	public Person(String name, String surname, Email email, Password password, long phonenumber) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phonenumber = phonenumber;
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
	
	public long getPhonenumber() {
		return phonenumber;
	}
	
	public void set(String name, String surname, Email email, Password password, long phoneNumber) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phonenumber = phoneNumber;
	}
	
	public PersonGui getGui() {
		return gui;
	}
	
	public boolean login(Person person) {
		return person.getEmail().equals(this.email) && person.getPassword().equals(this.password);
	}
	
	public abstract boolean equals(Object other);
	public abstract String toString();
	public abstract int compareTo(Person other);
}
