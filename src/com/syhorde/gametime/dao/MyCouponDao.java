package com.syhorde.gametime.dao;
import java.util.List;

import com.syhorde.gametime.vo.MyCoupon;
public interface MyCouponDao {
	public List<MyCoupon> getMyCoupon(String userCode);
	public MyCoupon getMyCouponByCode(String myCouponCode);
	public MyCoupon getAllMyCouponByCode(String myCouponCode);
	public void updateMyCouponStatusByCode(String myCouponCode);
}
