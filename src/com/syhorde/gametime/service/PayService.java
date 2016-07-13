package com.syhorde.gametime.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public interface PayService {
	
	public String payBack(HttpServletRequest request) throws UnsupportedEncodingException;
	
}
