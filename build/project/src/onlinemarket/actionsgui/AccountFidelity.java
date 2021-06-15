package onlinemarket.actionsgui;

import onlinemarket.account.*;
import onlinemarket.fidelitycard.FidelityCard;
import java.io.Serializable;
import onlinemarket.datentime.Date;


public class AccountFidelity extends Account implements Serializable{
	private static final long serialVerisonUID = 18L;
	private FidelityCard card;
	
	public AccountFidelity(String name, String surname, Email email, Password password, Long phonenumber, Address address, FidelityCard card) {
		super(name, surname, email, password, phonenumber, address);
		this.card = card;
	}
	
	public AccountFidelity(String name, String surname, Email email, Password password, Long phonenumber, Address address) {
		super(name, surname, email, password, phonenumber, address);
		this.card = new FidelityCard("MissingCard");
	}
		
	@Override
	public Long getPhoneNumber() {
		return super.getPhoneNumber();
	}
	
	public Integer getPoints() {
		return card.getPoints();
	}
	
	public Date getCardDate() {
		return card.getDate();
	}
	
	public String getCardID() {
		return card.getCardId();
	}
	
	
	public FidelityCard getFidelityCard() {
		return card;
	}
	
	public String toString() {
		return super.toString() + card.toString();
	}
	
	public int compareTo(AccountFidelity t) {
		return super.getEmail().toString().hashCode() - t.getEmail().toString().hashCode();
	}
	
}
