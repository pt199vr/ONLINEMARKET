package onlinemarket.calendar;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DataTime implements Comparable<DataTime>{
	private final Integer day,month,year,hour,minute;
	
	public DataTime() {
		LocalDateTime now=java.time.LocalDateTime.now();
		this.year=now.getYear();
		this.month=now.getMonthValue();
		this.day=now.getDayOfMonth();
		this.hour=now.getHour();
		this.minute=now.getMinute();
	}
	
	public DataTime(Integer day,Integer month, Integer year,Integer hour,Integer minute) throws IllegalArgumentException{
		this.day=day;
		this.month=month;
		this.year=year;
		this.hour=hour;
		this.minute=minute;
		if(month>12||month<1)throw new IllegalArgumentException("Month number must be between 1 and 12");
		if(hour>23||hour<0) throw new IllegalArgumentException("Hour number must be between 0 and 23");
		if(minute>59||minute<0)throw new IllegalArgumentException("Minute number must be between 0 and 59");
	}
	public DataTime(Calendar calendar) {
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		
	}
	public boolean isLeapyear() {
		return (year % 400 == 0)? true : ((year % 4 == 0) &&(year % 100 != 0)) ? true : false;
	}
	public String toString() {
		return String.format("%02d/%02d/%04d %02d:%02d", day,month,year,hour,minute);
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
	public Integer getHours() {
		return hour;
	}
	
	public Integer getMinutes() {
		return minute;
	}
	@Override
	public int compareTo(DataTime other) {
		int diff = year-other.year;
		if (diff!=0)
			return diff;
		diff=month-other.month;
		if (diff!=0)
				return diff;
		diff=day-other.day;
		if(diff!=0)
			return diff;
		diff= hour-other.hour;
		if(diff!=0)
			return diff;
		return minute-other.minute;
		
	}
	public boolean equals(Object other) {
		return other instanceof DataTime && ((DataTime)other).getYear().equals(this.getYear()) 
				&& ((DataTime)other).getMonth().equals(this.getMonth())
				&& ((DataTime)other).getDay().equals(this.getDay())
				&& ((DataTime)other).getHours().equals(this.getHours())
				&& ((DataTime)other).getMinutes().equals(this.getMinutes());
	}

}
