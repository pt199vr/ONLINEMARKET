package onlinemarket.person;

public abstract class Person {
	protected String name, surname;
	protected Email email;
	protected Password password;
	protected long phonenumber;
	
	
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
	
	
	public abstract boolean equals(Person other);
	public abstract String toString();
	public abstract int compareTo(Person other);
}
