package com.syhorde.gametime.dao;

import java.util.List;
import java.util.Map;

import com.syhorde.gametime.vo.Booking;

public interface BookingDao {
	
	public List<Booking> getBooking(Map<String, Object> params);
	
	public void gnrtBooking(List<Booking> bookings);
	
	public double getProductPrice(Map<String, Object> params);
	
	public List<String> getGoodsCode(Map<String, Object> params);
	
	public void lockGoods (List<String> goodsCodes);
	
	public void updateBookingStatusToO(String batch);
}
