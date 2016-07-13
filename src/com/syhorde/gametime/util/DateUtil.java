package com.syhorde.gametime.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {
	
	public static void main(String[] args) {
		System.out.println();
		DateUtil d = new DateUtil();
		d.d();

	}
	
	public void d() {
		System.out.println(super.toString());
	}
	
	public void DateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println("dateTime from the system is:"+dateTime);
		LocalDate ld = LocalDateTime.now().toLocalDate();//gets the date value(LocalDate) from the date time. A LocalDate with same year, month and day as this LocalDateTime object will be returned.
		System.out.println("The date field from the date time object is : "+ld.toString());
		LocalTime lt = dateTime.toLocalTime();// gets the time value(LocalTime) from the date time. returns a LocalTime with the same hour, minute, second and nanosecond as this date-time.
		System.out.println("The time field from the date time object is : "+lt.toString());
	}
}