package onlinemarket.calendar;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Time implements Comparable<Time>{
	private final Integer hours, minutes;
	
	public Time() {
		LocalDateTime now =	java.time.LocalDateTime.now();
		hours = now.getHour();
		minutes = now.getMinute();		
	}
	
	public Time(Integer hours, Integer minutes) {
		this.hours = hours;
		this.minutes = minutes;
		
	}
	
	public Time(Calendar calendar) {
		this.hours = calendar.get(Calendar.HOUR_OF_DAY);
		this.minutes = calendar.get(Calendar.MINUTE);
	}
	
	public Integer getHours() {
		return hours;
	}
	
	public Integer getMinutes() {
		return minutes;
	}
	
	public boolean equals(Object other) {
		return other instanceof Time && ((Time) other).getHours().equals(this.hours) && ((Time) other).getMinutes().equals(this.minutes);
	}
	
	public String toString() {
		return String.format("%02d:%02d", hours, minutes);
	}
	
	public int compareTo(Time other) {
		int diff = hours - other.getHours();
		if(diff != 0)
			return diff;
		return minutes - other.getMinutes();
	}
	
}
