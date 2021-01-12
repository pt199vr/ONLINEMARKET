package onlinemarket.payment;

import java.io.Serializable;
import onlinemarket.calendar.Date;

public class CreditCard implements Payment, Serializable{
	private static final long serialVersionUID = 7L;
	private final String ownerCard;
	private final Date expirationDate;
	private final Integer CVV2, id;
	
	
	public CreditCard(String ownerCard, Date expirationDate, Integer CVV2, Integer id) {
		this.ownerCard = ownerCard;
		this.expirationDate = expirationDate;
		this.CVV2 = CVV2;
		this.id = id;
	}
	
	public String getOwnerCard() {
		return ownerCard;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public Integer getCVV2() {
		return CVV2;
	}	
	
	public Integer getid() {
		return id;
	}
	
	public String toString() {
		return String.format("%s %016d %03d %s", ownerCard, expirationDate, CVV2, id);
	}
	
	public boolean equals(Object other) {
		return other instanceof CreditCard && ((CreditCard) other).ownerCard.equals(ownerCard) 
				&& ((CreditCard) other).expirationDate.equals(expirationDate)
				&& ((CreditCard) other).id.equals(id)
				&& ((CreditCard) other).CVV2.equals(CVV2);
	}
		
}
