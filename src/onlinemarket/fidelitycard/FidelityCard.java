package onlinemarket.fidelitycard;


import onlinemarket.account.*;
import onlinemarket.Main;
import java.io.Serializable;

public class FidelityCard implements Serializable, Comparable<FidelityCard>{
	private static final long serialVerisonUID = 16L;
	
	private int points;
	private String cardId;
	private String account;
	private Integer year, month, day;
	
	public FidelityCard(Account account) {
		this.account = account.toString();
		this.points = 0;
		this.cardId = Main.createFidelityId();
		this.year = java.time.LocalDateTime.now().getYear();
		this.month = java.time.LocalDateTime.now().getMonthValue();
		this.day = java.time.LocalDateTime.now().getDayOfMonth();
		
	}
	
	public String getAccount() {
		return account;
	}
		
	public int getPoints() {
		return points;
	}
	
	public String getCardId() {
		return cardId;
	}
	
	public int compareTo(FidelityCard t) {
		return cardId.hashCode() - t.getCardId().hashCode();
	}
	
	public boolean equals(Object t) {
		return t instanceof FidelityCard && ((FidelityCard) t).getCardId().equals(this.cardId);
	}
	
	public String toString() {
		return points + " " + String.format("%02d/%02d/%d", day, month, year) +  " " + cardId;
	}
	
}
