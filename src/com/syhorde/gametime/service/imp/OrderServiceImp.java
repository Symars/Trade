package com.syhorde.gametime.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syhorde.gametime.dao.BookingDao;
import com.syhorde.gametime.dao.OrderDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.pay.alipay.AlipayApi;
import com.syhorde.gametime.service.OrderService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.util.GUID;
import com.syhorde.gametime.util.StringUtil;
import com.syhorde.gametime.vo.Booking;
import com.syhorde.gametime.vo.Order;

@Service("orderService")
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BookingDao bookingDao;
	
	private List<Order> orders;

	private List<Booking> bookings;
	
	private Order order;
	
	/**
	 * 生成订单
	 */
	@Override
	public String gnrtOrder(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			String bookingBatch = request.getParameter("BookingBatch");
			String userCode = request.getParameter("userCode");
			String addressCode = request.getParameter("AddressCode");
			String couponCode = request.getParameter("CouponCode");
			String payWay = request.getParameter("PayWay");
			String note = request.getParameter("Note");
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("userCode", userCode);
			params.put("date", LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ISO_DATE_TIME));
			params.put("batch", bookingBatch);
			
			bookings = bookingDao.getBooking(params);
			
			/**
			 * 获取当前时间
			 */
			String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
			
			/**
			 * 预订单批次码
			 */
			String batch = GUID.getUUID();
			
			Double price = 0.00;
			
			StringBuffer subject = new StringBuffer();

			for(Booking booking : bookings) {
				order = new Order();
				
				price += (booking.getBookingPrice() * booking.getBookingNum());
				
				subject.append(booking.getBookingName() + ",");
				
				/**
				 * 订单编码
				 */
				order.setOrderCode(GUID.getUUID());
				/**
				 * 订单批次码
				 */
				order.setOrderBatch(batch);
//				order.setOrderStartDate(orderStartDate);
//				order.setOrderEndDate(orderEndDate);
				/**
				 * 优惠券信息
				 */
				order.setCouponCode(couponCode);
				
				order.setOrderDate(now);
				order.setOrderName(booking.getBookingName());
				
				order.setUserCode(userCode);
				
				order.setProductCode(booking.getProductCode());
				order.setProductItemCode(booking.getProductItemCode());
				
				/**
				 * 订单价格减去减免价格
				 */
				order.setOrderPrice(booking.getBookingPrice());
				
				/**
				 * 减免价格
				 */
//				order.setOrderPriceCut();
				
				order.setAddressCode(addressCode);
				
				order.setOrderType(booking.getBookingType());
				
				/**
				 * 未付款
				 */
				order.setOrderStatus("0");
				
				orders.add(order);
			}
			
			orderDao.gnrtOrder(orders);
			
			/**
			 * 使用优惠券
			 */
			if(StringUtil.isNotEmpty(couponCode)) {
				/**
				 * 总价减去优惠券价格
				 */
//				price -= 123;
			}
			
			/**
			 * 支付地址
			 */
			if(payWay.equals(0)) {
				
				resultMap.put("PayUrl", AlipayApi.getUrl(batch, subject.deleteCharAt(subject.length() - 1).toString(), String.format("%.2f", price), note));
				
			} else {
				resultMap.put("PayUrl", "");
			}
			
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "数据添加成功");
			
			return JsonBuilder.toJson(resultMap, callback);
			
		} else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

	
	/**
	 * 加载订单
	 */
	@Override
	public String getOrder(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			
			
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "数据加载成功");
			
			return JsonBuilder.toJson(resultMap, callback);
			
		} else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

	/**
	 * 删除订单
	 */
	@Override
	public String deleteOrder(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			String orderBatch = request.getParameter("OrderBatch");
			
			orderDao.deleteOrder(orderBatch);
			
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "数据删除成功");
			
			return JsonBuilder.toJson(resultMap, callback);
			
		} else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

}
