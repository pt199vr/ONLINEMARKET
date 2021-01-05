package onlinemarket.payment;

import onlinemarket.person.Email;
import onlinemarket.person.Password;

public class PayPal implements Payment{
	
	private final Email email;
	private final Password password;
	
	public PayPal(Email email, Password password) {
		this.email = email;
		this.password = password;
	}
	
	public Email getEmail() {
		return email;
	}
	
	public Password getPassword() {
		return password;
	}
	
	public String toString() {
		return String.format("%s %s", email, password);
	}
	
	public boolean equals(Object other) {
		return other instanceof PayPal && ((PayPal) other).email.equals(this.email) && ((PayPal) other).password.equals(this.password);
	}
	
	
}
