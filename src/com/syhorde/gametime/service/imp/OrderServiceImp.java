package com.syhorde.gametime.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syhorde.gametime.dao.BookingDao;
import com.syhorde.gametime.dao.MyCouponDao;
import com.syhorde.gametime.dao.MyWalletDao;
import com.syhorde.gametime.dao.OrderDao;
import com.syhorde.gametime.dao.UserVIPDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.pay.alipay.AlipayApi;
import com.syhorde.gametime.pay.alipay.AlipayConfig;
import com.syhorde.gametime.service.OrderService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.util.GUID;
import com.syhorde.gametime.util.StringUtil;
import com.syhorde.gametime.vo.Booking;
import com.syhorde.gametime.vo.MyCoupon;
import com.syhorde.gametime.vo.MyWallet;
import com.syhorde.gametime.vo.Order;

@Service("orderService")
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private MyCouponDao myCouponDao;
	@Autowired
	private MyWalletDao myWalletDao;
	@Autowired
	private UserVIPDao userVIPDao;
	
	private MyCoupon myCoupon;
	
	private MyWallet myWallet;
	
	private List<Order> orders;

	private List<Booking> bookings;
	
	private Order order;
	
	private int vip;
	
	private int currentRentOrder;
	
	/**
	 * 生成订单
	 */
	@Transactional
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
//			String payWay = request.getParameter("PayWay");
			String note = request.getParameter("Note");
			
			/**
			 * 获取用户身份级别 ，是否为会员，当期是否有租赁订单
			 */
			vip = userVIPDao.getUserVIP(userCode);
			currentRentOrder = userVIPDao.getCurrentRentOrder(userCode);
			
			if (vip > 0 && currentRentOrder > 0) {
				resultMap.put(DicCons.RESULT_CODE, 110);
				resultMap.put(DicCons.RESULT_DESC, "存在正在租赁的游戏，无法再次租赁");
				return JsonBuilder.toJson(resultMap, callback);
			}
			
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
			
			double price = 0.00;
			
			double rprice = 0.00; 
			
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
				order.setOrderName(booking.getBookingName());
				
				order.setOrderStartDate(now);
//				order.setOrderEndDate(orderEndDate);
				order.setOrderDate(now);

				order.setUserCode(userCode);
				order.setUserName(booking.getUserName());
				
				/**
				 * 优惠券信息
				 */
				order.setCouponCode(couponCode);
				
				order.setProductCode(booking.getProductCode());
				order.setProductItemCode(booking.getProductItemCode());
				
				/**
				 * 订单价格减去减免价格
				 */
				order.setOrderPrice(booking.getBookingPrice());
				order.setOrderNum(booking.getBookingNum());
				
				/**
				 * 减免价格
				 */
//				order.setOrderPriceCut();
				
				order.setAddressCode(addressCode);
				
				String type = booking.getBookingType();
				
				if (type.equals("R")) {
					rprice += (booking.getBookingPrice() * booking.getBookingNum());
				}
				
				order.setOrderType(type);
				
				/**
				 * 未付款
				 */
				order.setOrderStatus("U");
				
				order.setOrderNote(note);
				
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
				myCoupon = myCouponDao.getMyCouponByCode(couponCode); 
//				price -= 123;
				
				if (myCoupon == null) {
					resultMap.put(DicCons.RESULT_CODE, 210);
					resultMap.put(DicCons.RESULT_DESC, "优惠券不可用");
					
					return JsonBuilder.toJson(resultMap, callback);
				} else {
					price -= myCoupon.getCouponAmount();
					myCouponDao.updateMyCouponStatusByCode(couponCode);
				}
			}
			
			/**
			 * 钱包余额
			 */
			myWallet = myWalletDao.getMyWallet(userCode);
			if (myWallet == null) {
				/**
				 * 支付地址
				 */
				myWallet.setUserCode(userCode);
				myWallet.setWalletCode(GUID.getUUID());
				myWallet.setWalletCrtDate(now);
				myWallet.setWalletUpdDate(now);
				myWallet.setWalletAmount(0);
				myWallet.setWalletPledge(0);
				myWalletDao.updateMyWallet(myWallet);
				resultMap.put("PayUrl", AlipayApi.getUrl(batch, subject.deleteCharAt(subject.length() - 1).toString(), String.format("%.2f", price), note, userCode, AlipayConfig.notify_url_order));
				resultMap.put(DicCons.RESULT_CODE, 220);
				resultMap.put(DicCons.RESULT_DESC, "钱包余额不足，请充值");
				
				return JsonBuilder.toJson(resultMap, callback);
			} else {
				
				if (price > myWallet.getWalletAmount()) {
						
					resultMap.put("PayUrl", AlipayApi.getUrl(batch, subject.deleteCharAt(subject.length() - 1).toString(), String.format("%.2f", price - myWallet.getWalletAmount()), note, userCode, AlipayConfig.notify_url_order));
					resultMap.put(DicCons.RESULT_CODE, 220);
					resultMap.put(DicCons.RESULT_DESC, "钱包余额不足，请充值");
					
					return JsonBuilder.toJson(resultMap, callback);
				} else {
					
					/**
					 * 通过钱包扣款
					 */
					double balance = myWallet.getWalletAmount() - price;
					
					myWallet.setWalletAmount(balance);
					myWallet.setWalletPledge(rprice);
					myWallet.setWalletUpdDate(now);
					
					myWalletDao.updateMyWallet(myWallet);
					
					/**
					 * 更改订单为支付状态
					 */
					orderDao.updateOrdersStatusToPay(batch);
					
					resultMap.put(DicCons.RESULT_CODE, 100);
					resultMap.put(DicCons.RESULT_DESC, "支付成功");
					return JsonBuilder.toJson(resultMap, callback);
				}
			}
			
		} else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

	@Transactional
	@Override
	public String payOrder(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			String orderBatch = request.getParameter("OrderBatch");
			String userCode = request.getParameter("userCode");
//			String couponCode = request.getParameter("CouponCode");
			
			/**
			 * 获取用户身份级别 ，是否为会员，当期是否有租赁订单
			 */
			vip = userVIPDao.getUserVIP(userCode);
			currentRentOrder = userVIPDao.getCurrentRentOrder(userCode);
			
			if (vip > 0 && currentRentOrder > 0) {
				resultMap.put(DicCons.RESULT_CODE, 110);
				resultMap.put(DicCons.RESULT_DESC, "存在正在租赁的游戏，无法再次租赁");
				return JsonBuilder.toJson(resultMap, callback);
			}
			
			orders = orderDao.getOrderByBatch(orderBatch);
			
			/**
			 * 获取当前时间
			 */
			String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
			
			/**
			 * 预订单批次码
			 */
			String batch = GUID.getUUID();
			
			double price = 0.00;
			
			double rprice = 0.00; 
			
			String couponCode = "";
			
			String note = "";
			
			StringBuffer subject = new StringBuffer();
			
			for(Order order: orders) {
				
				price += (order.getOrderPrice() * order.getOrderNum());
				
				subject.append(order.getOrderName() + ",");
				
				String type = order.getOrderType();
				
				if (type.equals("R")) {
					rprice += (order.getOrderPrice() * order.getOrderNum());
				}
				
				note = order.getOrderNote();
				
				couponCode = order.getCouponCode();
			}
			
			/**
			 * 使用优惠券
			 */
			if(StringUtil.isNotEmpty(couponCode)) {
				/**
				 * 总价减去优惠券价格
				 */
				myCoupon = myCouponDao.getMyCouponByCode(couponCode); 
//				price -= 123;
				
				if (myCoupon == null) {
					resultMap.put(DicCons.RESULT_CODE, 210);
					resultMap.put(DicCons.RESULT_DESC, "优惠券不可用");
					
					return JsonBuilder.toJson(resultMap, callback);
				} else {
					price -= myCoupon.getCouponAmount();
					myCouponDao.updateMyCouponStatusByCode(couponCode);
				}
			}
			
			/**
			 * 钱包余额
			 */
			myWallet = myWalletDao.getMyWallet(userCode);
			if (myWallet == null) {
				/**
				 * 支付地址
				 */
				myWallet.setUserCode(userCode);
				myWallet.setWalletCode(GUID.getUUID());
				myWallet.setWalletCrtDate(now);
				myWallet.setWalletUpdDate(now);
				myWallet.setWalletAmount(0);
				myWallet.setWalletPledge(0);
				myWalletDao.updateMyWallet(myWallet);
				
				resultMap.put("PayUrl", AlipayApi.getUrl(batch, subject.deleteCharAt(subject.length() - 1).toString(), String.format("%.2f", price), note, userCode, AlipayConfig.notify_url_order));
				resultMap.put(DicCons.RESULT_CODE, 220);
				resultMap.put(DicCons.RESULT_DESC, "钱包余额不足，请充值");
				
				return JsonBuilder.toJson(resultMap, callback);
			} else {
				
				if (price > myWallet.getWalletAmount()) {
						
					resultMap.put("PayUrl", AlipayApi.getUrl(batch, subject.deleteCharAt(subject.length() - 1).toString(), String.format("%.2f", price), note, userCode, AlipayConfig.notify_url_order));
					resultMap.put(DicCons.RESULT_CODE, 220);
					resultMap.put(DicCons.RESULT_DESC, "钱包余额不足，请充值");
					
					return JsonBuilder.toJson(resultMap, callback);
				} else {
					
					/**
					 * 通过钱包扣款
					 */
					double balance = myWallet.getWalletAmount() - price;
					
					myWallet.setWalletAmount(balance);
					myWallet.setWalletPledge(rprice);
					myWallet.setWalletUpdDate(now);
					
					myWalletDao.updateMyWallet(myWallet);
					
					/**
					 * 更改订单为支付状态
					 */
					orderDao.updateOrdersStatusToPay(batch);
					
					resultMap.put(DicCons.RESULT_CODE, 100);
					resultMap.put(DicCons.RESULT_DESC, "支付成功");
					return JsonBuilder.toJson(resultMap, callback);
				}
			}
			
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
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put(DicCons.USER_CODE, (String)request.getParameter(DicCons.USER_CODE));
			params.put("OrderBatch", (String)request.getParameter("OrderBatch"));
			
			orders = orderDao.getOrder(params);
			
			resultMap.put("OrderList", orders);
			
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
