package com.syhorde.gametime.util;

public class StringUtil {
	
	public static boolean isEmpty(String s) {
		if (s == null || "".equals(s)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(String s) {
		if (s == null || "".equals(s)) {
			return false;
		}
		return true;
	}
}
