package com.syhorde.gametime.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public interface PayService {
	
	public String payBack(HttpServletRequest request) throws UnsupportedEncodingException;

	public String payBackUpToVIP(HttpServletRequest request) throws UnsupportedEncodingException;

	public String payBackBuyUsed(HttpServletRequest request) throws UnsupportedEncodingException;
	
}
