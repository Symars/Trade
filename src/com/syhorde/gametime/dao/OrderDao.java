package com.syhorde.gametime.dao;

import java.util.List;
import java.util.Map;

import com.syhorde.gametime.vo.Order;

public interface OrderDao {
	
	public void gnrtOrder(List<Order> orders);
	
	public List<Order> getOrder(Map<String, Object> params);
	
	public void deleteOrders(String batch);

	public void deleteOrder(String orderCode);
	
	public void updateOrderStatus(Map<String, Object> params);

	public void updateOrdersStatusToPay(String batch);
	
}