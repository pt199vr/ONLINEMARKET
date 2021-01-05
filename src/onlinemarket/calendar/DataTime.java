package onlinemarket.calendar;

import onlinemarket.calendar.*;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DataTime implements Comparable<DataTime>{
	private final Date date;
	private final Time time;
	
	public DataTime() {
		date = new Date();
		time = new Time();
	}
	
	
	
	
	
	public DataTime(Integer day, Integer month, Integer year, Integer hour, Integer minute) {
		date = new Date(year, month, day);
		time = new Time(hour, minute);
	}
	
	public DataTime(Date date, Time time) {
		this.date = date;
		this.time = time;
	}
	
	public DataTime(Calendar calendar) {
		this(calendar.get(Calendar.YEAR), 
			 calendar.get(Calendar.MONTH) + 1,
			 calendar.get(Calendar.DAY_OF_MONTH),
			 calendar.get(Calendar.HOUR_OF_DAY),
			 calendar.get(Calendar.MINUTE));
		
	}
	
	public Integer getDay() {
		return date.getDay();
	}
	
	public Integer getMonth() {
		return date.getMonth();
	}
	
	public Integer getYear() {
		return date.getYear();
	}
	public Integer getHours() {
		return time.getHours();
	}
	
	public Integer getMinutes() {
		return time.getMinutes();
	}
	
	
	public Date getDate() {
		return date;
	}
	
	public Time getTime() {
		return time;
	}
	
	public String toString() {
		return date + "   " + time;
	}
	
	
	@Override
	public int compareTo(DataTime other) {
		int diff = date.compareTo(other.getDate());
		
		if(diff != 0)
			return diff;
		else
			return time.compareTo(other.getTime());
	}
	
	
	
	public boolean equals(Object other) {
		return other instanceof DataTime && ((DataTime)other).getDate().equals(this.date) 
				&& ((DataTime)other).getTime().equals(this.time);
				
	}
}
