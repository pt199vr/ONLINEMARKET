package onlinemarket.person.user;

import onlinemarket.person.*;

public class User extends Person{
	private Address address;
	private FidelityCard fidelityCard;
	private Payment payment;
	private Cart cart;
	
	public User(String name, String surname, Email email, Password password, long phoneNumber, Address address) {
		super(name, surname, email, password, phoneNumber);
		
		this.address = address;
		this.cart = new Cart();
		this.fidelityCard = null;
		this.payment = null;
	}
	
	//ADDRESS
	public Address getAddress() {
		return address;
	}
	
	
	//SET
	public void set(String name, String surname, Email email, Password password, long phoneNumber, Address address) {
		super.set(name, surname, email, password, phoneNumber);
		
		this.address = address;
	}
	
	
	//PAYMENT
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	
	//CART
	public Cart getCart() {
		return cart;
	}
	
	//EQUALS
	public boolean equals(Object other) {
		return other instanceof User && ((User) other).getEmail().equals(this.getEmail());
	}
	
	//TOSTRING
	public String toString() {
		return String.format("%s %s %s %s %s %s %s", name, surname, email, phonenumber, address, fidelityCard, payment);
	}
	//COMPARETO
	public int compareTo(Person other) {
		return email.compareTo(other.getEmail());
	}
	
	
	//FIDELITY CARD
	public int hashCode() {
		return email.hashCode();
	}
	
	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}
	
	public void createFidelityCard() {
		if(fidelityCard == null)
			fidelityCard = new FidelityCard();
	}
	
}
