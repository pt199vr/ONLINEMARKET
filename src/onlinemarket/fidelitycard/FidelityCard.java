package onlinemarket.fidelitycard;

import onlinemarket.calendar.Date;

public class FidelityCard {
	
	private final Integer id_number;
	private Integer points;
	private final Date date;
	
	
	public FidelityCard(Integer id_number, Date date) {
		this.points = 0;
		this.id_number = id_number;
		this.date = date;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public Integer getIDnumber() {
		return id_number;
	}
	
	public Date getDate() {
		return date;
	}
	
	public boolean equals(Object other) {
		return other instanceof FidelityCard && ((FidelityCard) other).getIDnumber().equals(this.id_number);
}
	
	public String toString() {
		return String.format("%s %d %s", id_number, points, getDate());
	}
	
	public void addPoints(Integer add) {
		this.points += add;
	}
	
	public void removePoints(Integer rem) {
		this.points -= rem;
	}
	
	
}
