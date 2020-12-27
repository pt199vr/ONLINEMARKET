package onlinemarket.person;

public class Email implements Comparable<Email>{
	private final String email;
	
	public Email(String email) throws IllegalArgumentException{
		int pos = email.indexOf('@', 0);
		
		if(pos == -1)
			throw new IllegalArgumentException("Email must have '@'");
		
		pos = email.indexOf('.', pos);
		if(pos == -1)
			throw new IllegalArgumentException("Email must have a '.' after '@'");
		
		if(pos + 1 == email.length())
			throw new IllegalArgumentException("Email must have a character after '.'");
		
		this.email = email;
	}
	
	public int compareTo(Email other) {
		return toString().compareTo(other.toString());
	}
	
	public boolean equals(Object other) {
		return other instanceof Email && other.toString().equals(this.toString());
	}
	
	@Override
	public String toString() {
		return email;
	}
	
}
