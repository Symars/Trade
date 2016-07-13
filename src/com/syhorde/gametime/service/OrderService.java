package com.syhorde.gametime.service;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
	public String gnrtOrder(HttpServletRequest request);
	
	public String getOrder(HttpServletRequest request);
	
	public String deleteOrder(HttpServletRequest request);
}
