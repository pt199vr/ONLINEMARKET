package onlinemarket.datentime;

import java.io.Serializable;

public class DaynHour implements Serializable{
	private static final long serialVersionUID = 11L;
	private final Time time;
	private final Date date;
	

	public DaynHour(Integer year, Integer month, Integer day, Integer hours, Integer minutes) {
		time = new Time(hours, minutes);
		date = new Date(year, month, day);
	}
	
	public DaynHour(Date date, Time time) {
		this.date = date;
		this.time = time;
	}
	
	public Integer getHours() {
		return time.getHours();
	}
	
	public Integer getMinutes() {
		return time.getMinutes();
	}
	
	public Integer getYear() {
		return date.getYear();
	}
	
	public Integer getMonth() {
		return date.getMonth();
	}
	
	public Integer getDay() {
		return date.getDay();
	}
	
	public Time getTime() {
		return time;
	}
	
	public Date getDate() {
		return date;
	}
	
	public boolean equals(Object other) {
		return other instanceof DaynHour && ((DaynHour) other).getTime().equals(this.time) && ((DaynHour) other).getDate().equals(this.date);
	}
	
	public int compareTo(DaynHour other) {
		int diff = date.compareTo(other.getDate());
		if (diff != 0)
			return diff;

		return time.compareTo(other.getTime());
	}
	
	public String toString() {
		return date + " - " + time;
	}
	
}
