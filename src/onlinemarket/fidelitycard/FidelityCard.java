package onlinemarket.fidelitycard;


import onlinemarket.account.*;
import onlinemarket.Main;
import java.io.Serializable;
import onlinemarket.datentime.*;

public class FidelityCard implements Serializable, Comparable<FidelityCard>{
	private static final long serialVersionUID = 16L;
	
	private int points;
	private String cardId;
	private String account;
	private Date date;
	
	public FidelityCard(Account account) {
		this.account = account.toString();
		this.points = 0;
		this.cardId = Main.createFidelityId();
		int year = java.time.LocalDateTime.now().getYear();
		int month = java.time.LocalDateTime.now().getMonthValue();
		int day = java.time.LocalDateTime.now().getDayOfMonth();
		this.date = new Date(year,month,day);
		
	}
	
	public FidelityCard(String s) {
		if(s.equals("MissingCard")) {
			points = 0;
			cardId = "Missing";
			account = "";
			date = new Date(0, 0, 0);
		}
	}
	
	public String getAccount() {
		return account;
	}
		
	public int getPoints() {
		return points;
	}
	public void setPoints(int newpoints) {
		this.points = newpoints;
	}
	
	public String getCardId() {
		return cardId;
	}
	public Date getDate() {
		return date;
	}
	
	public int compareTo(FidelityCard t) {
		return cardId.hashCode() - t.getCardId().hashCode();
	}
	
	public boolean equals(Object t) {
		return t instanceof FidelityCard && ((FidelityCard) t).getCardId().equals(this.cardId);
	}
	
	public String toString() {
		return points + " " + date.toString() +  " " + cardId;
	}
	
}
