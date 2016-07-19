package com.syhorde.gametime.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syhorde.gametime.dao.MyCouponDao;
import com.syhorde.gametime.dao.MyTradeDao;
import com.syhorde.gametime.dao.MyWalletDao;
import com.syhorde.gametime.dao.OrderDao;
import com.syhorde.gametime.dao.UserVIPDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.pay.alipay.AlipayApi;
import com.syhorde.gametime.pay.alipay.AlipayConfig;
import com.syhorde.gametime.service.UserVIPService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.util.GUID;
import com.syhorde.gametime.util.StringUtil;
import com.syhorde.gametime.vo.MyCoupon;
import com.syhorde.gametime.vo.MyTrade;
import com.syhorde.gametime.vo.MyWallet;
import com.syhorde.gametime.vo.Order;
import com.syhorde.gametime.vo.UserVIP;

@Service("userVIPService")
public class UserVIPServiceImp implements UserVIPService {

	@Autowired
	private UserVIPDao userVIPDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private MyWalletDao myWalletDao;
	@Autowired
	private MyTradeDao myTradeDao;
	@Autowired
	private MyCouponDao myCouponDao;
	
	private MyCoupon myCoupon;
	
	private MyTrade myTrade;
	
	private MyWallet myWallet;
	
	private Order order;
	
	private UserVIP userVIP;
	
	@Transactional
	@Override
	public String upToVIP(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			String userCode = request.getParameter(DicCons.USER_CODE);
			
			String coupon = request.getParameter("CouponCode");
			
			/**
			 * 申请年数
			 */
			String num = request.getParameter("num");
			
			double perprice = 1000;
			
			int count = 1;
			
			if(StringUtil.isEmpty(num)) {
				count = 1;
			} else {
				count = Integer.parseInt(num);
			}
			
			double price = perprice * count;
			

			/**
			 * 获取当前时间
			 */
			String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
			
			String batch = GUID.getUUID();
			
			String name = "会员申请";
			
			order = new Order();

			order.setOrderCode(batch);
			order.setOrderBatch(batch);
			order.setOrderName("年费会员");
			order.setOrderDate(now);
			order.setOrderStartDate(now);
			order.setOrderEndDate("");
			order.setOrderPrice(perprice);
			order.setOrderNum(count);
			order.setOrderStatus("U");
			order.setOrderType("B");
			order.setCouponCode(coupon);
			
			order.setProductCode("");
			order.setProductItemCode("");
			order.setProductCode("0");
			order.setOrderPriceCut(0.0);
			order.setAddressCode("");
			order.setGoodsCode("");
			
			/**
			 * 使用优惠券
			 */
			if(StringUtil.isNotEmpty(coupon)) {
				/**
				 * 总价减去优惠券价格
				 */
				myCoupon = myCouponDao.getMyCouponByCode(coupon);
				if (myCoupon != null) {
					price -= myCoupon.getCouponAmount();
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
				resultMap.put("PayUrl", AlipayApi.getUrl(batch, name, String.format("%.2f", price), name, userCode, AlipayConfig.notify_url_vip));
				resultMap.put(DicCons.RESULT_CODE, 220);
				resultMap.put(DicCons.RESULT_DESC, "钱包余额不足，请充值");
				
				return JsonBuilder.toJson(resultMap, callback);
			} else {

				if (price > myWallet.getWalletAmount()) {
					
					resultMap.put("PayUrl", AlipayApi.getUrl(batch, name, String.format("%.2f", price), name, userCode, AlipayConfig.notify_url_vip));
					resultMap.put(DicCons.RESULT_CODE, 220);
					resultMap.put(DicCons.RESULT_DESC, "钱包余额不足，请充值");
					
					return JsonBuilder.toJson(resultMap, callback);
				} else {
					
					/**
					 * 通过钱包扣款
					 */
					double balance = myWallet.getWalletAmount() - price;
					
					myWallet.setWalletAmount(balance);
					myWallet.setWalletUpdDate(now);
					
					myWalletDao.updateMyWallet(myWallet);
					
					/**
					 * 添加用户u消费记录
					 */
					myTrade = new MyTrade();
					
					myTrade.setOrderCode(batch);
					myTrade.setTradeAmount(price);
					myTrade.setTradeCode(GUID.getUUID());
					myTrade.setTradeCrtDate(now);
					myTrade.setTradeUpdDate(now);
					myTrade.setTradeType("V");
					myTrade.setUserCode(userCode);
					
					myTradeDao.insertMyTrade(myTrade);
					
					/**
					 * 更新vip信息
					 */
					
					userVIP = userVIPDao.getUserVIPInfo(userCode);
					
					LocalDateTime start = LocalDateTime.now();
					
					String startDate = start.toString();

					String endDate = "";
					
					if(userVIP != null) {
						startDate = start.toString();
						
						endDate = LocalDateTime.parse(userVIP.getEndDate()).plusYears(count).toString();
						
						
					} else {
						endDate = start.plusYears(count).toString();
					}
					
					userVIP.setVipCode(GUID.getUUID());
					userVIP.setUserCode(userCode);
					userVIP.setStartDate(startDate);
					userVIP.setEndDate(endDate);
					
					userVIPDao.insertUserVIP(userVIP);
					
					/**
					 * 更改订单为支付状态
					 */
					orderDao.updateOrdersStatusToFinish(batch);
					
					resultMap.put(DicCons.RESULT_CODE, 100);
					resultMap.put(DicCons.RESULT_DESC, "会员申请成功");
					return JsonBuilder.toJson(resultMap, callback);
					
				}
				
			}
			
		}else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

}
