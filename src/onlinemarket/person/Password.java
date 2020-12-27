package onlinemarket.person;

public class Password implements Comparable<Password> {
	private final String password;
	
	public Password(String password) throws IllegalArgumentException{
		if(password.length() < 6)
			throw new IllegalArgumentException("Password must be minimum 6 characters");
		
		this.password = password;
	}
	
	public int compareTo(Password other) {
		return this.toString().compareTo(other.toString());
	}
	
	public boolean equals(Object other) {
		return other instanceof Password && other.toString().equals(this.toString());
	}
	
	@Override
	public String toString() {
		return password;
	}
}
