package onlinemarket.calendar;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Date implements Comparable<Date>, Serializable{
	private static final long serialVersionUID = 1L;
	private final Integer year, month, day;
	
	public Date() {
		LocalDateTime now =	java.time.LocalDateTime.now();
		year = now.getYear();
		month = now.getMonthValue();
		day = now.getDayOfMonth();
	}
	
	public Date(Integer year, Integer month, Integer day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public Date(Calendar calendar) {
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		
	}
	
	
	public boolean isLeapyear() {
		return (year % 400 == 0)? true : ((year % 4 == 0) &&(year % 100 != 0)) ? true : false;
	}
	
	
	public Integer getDay() {
		return day;
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public int compareTo(Date other) {
		int diff = year - other.year;
		if(diff != 0)
			return diff;
		
		diff = month - other.month;
		if(diff != 0)
			return diff;
		
		return day - other.day;
	}
	
	public boolean equals(Object other) {
		return other instanceof Date && ((Date) other).getDay().equals(this.getDay()) && ((Date) other).getMonth().equals(this.getMonth()) && ((Date) other).getYear().equals(this.getYear());
			
	}
	
	public String toString() {
		return String.format("%02d/%02d/%04d", day, month, year);
	}
	
	
	
	
	
}
