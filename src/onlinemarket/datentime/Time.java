package onlinemarket.datentime;

import java.io.Serializable;

public class Time {
	private static final long serialVersionUID = 9L;
	private final Integer hours, minutes;
	
	public Time(int h, int m) {
		this.hours = h;
		this.minutes = m;
	}
	
	public Integer getHours() {
		return hours;
	}
	
	public Integer getMinutes() {
		return minutes;
	}
	
	public boolean equals(Object other) {
		return other instanceof Time && ((Time) other).getMinutes().equals(this.minutes) && ((Time) other).getHours().equals(this.hours);
	}
	
	public int compareTo(Time other) {
		int diff = hours - other.hours;
		if (diff != 0)
			return diff;

		return minutes - other.minutes;
	}
	
	public String toString() {
		return String.format("%02d:%02d", hours, minutes);
	}
	
}
