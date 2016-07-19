package com.syhorde.gametime.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {
	
	public static int diffDateTime(LocalDateTime startDate, LocalDateTime endDate) {
		
		double diffSecond = endDate.toEpochSecond(ZoneOffset.UTC) - startDate.toEpochSecond(ZoneOffset.UTC);
		
		double diffDays = diffSecond / (24 * 60 * 60);
		
		return  (int)Math.ceil(diffDays);
	}
	
}