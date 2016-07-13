package com.syhorde.gametime.util;

import java.util.Random;

public class CaptchaBuilder {
	
	private static final ThreadLocal<CaptchaBuilder> local = new ThreadLocal<CaptchaBuilder>();
	
	private CaptchaBuilder() {
		super();
	}
	
	public static CaptchaBuilder getCaptcha() {
		CaptchaBuilder captcha = local.get();
		if(captcha == null) {
			captcha = new CaptchaBuilder();
			local.set(captcha);
		}
		return captcha;
	}
	
	public static String captcha(int length) {
		 Random random = new Random();   
		 StringBuffer sb = new StringBuffer(); 
		 for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(DicCons.ALPHANUM.length());   
	        sb.append(DicCons.ALPHANUM.charAt(number));   
	    }   
	    return sb.toString();   
	}
}
