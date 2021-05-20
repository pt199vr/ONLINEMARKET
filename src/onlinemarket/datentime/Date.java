package onlinemarket.datentime;

import java.io.Serializable;

public class Date implements Serializable{
	private static final long serialVersionUID = 10L;
	private final Integer year, month, day;
	
	public Date(Integer y, Integer m, Integer d) {
		this.year = y;
		this.month = m;
		this.day = d;		
	}
	
	public Date() {
		this.year = java.time.LocalDateTime.now().getYear();
		this.month = java.time.LocalDateTime.now().getMonthValue();
		this.day = java.time.LocalDateTime.now().getDayOfMonth();
	}
	
	public Integer getYear() {
		return year;
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public Integer getDay() {
		return day;
	}
	
	public boolean equals(Object other) {
		return other instanceof Date && ((Date) other).getDay().equals(this.day) && ((Date) other).getMonth().equals(this.month) && ((Date) other).getYear().equals(this.year);
	}
	
	public int compareTo(Date other) {
		int diff = year - other.year;
		if (diff != 0)
			return diff;

		diff = month - other.month;
		if (diff != 0)
			return diff;

		return day - other.day;
	}
	
	public String toString() {
		return String.format("%02d/%02d/%d", day, month, year);
	}
}
