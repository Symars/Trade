package com.syhorde.gametime.service.imp;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syhorde.gametime.dao.MyCouponDao;
import com.syhorde.gametime.dao.MyWalletDao;
import com.syhorde.gametime.dao.OrderDao;
import com.syhorde.gametime.pay.alipay.AlipayNotify;
import com.syhorde.gametime.service.PayService;
import com.syhorde.gametime.util.StringUtil;
import com.syhorde.gametime.vo.MyCoupon;
import com.syhorde.gametime.vo.MyWallet;
import com.syhorde.gametime.vo.Order;

@Service("payService")
public class PayServiceImp implements PayService {
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private MyWalletDao myWalletDao;
	@Autowired
	private MyCouponDao myCouponDao;
	
	private List<Order> orders;
	
	private MyWallet myWallet;
	
	private MyCoupon myCoupon;
	
	//商户订单号
	private String orderBatch;

	//支付宝交易号
	private String trade_no;

	//交易状态
	private String trade_status;
	
	//商品名称
	private String subject;
	
	//支付类型
	private String payment_type;
	
	//交易创建时间
	private String gmt_create;
	
	
	//交易付款时间
	private String gmt_payment;
	
	//交易付款时间
	private String gmt_close;

	//交易金额
	private String total_fee;
	
	//是否扫码支付
	private String business_scene;
	
	//userCode
	private String userCode;
	
	@Transactional
	@Override
	public String payBack(HttpServletRequest request) throws UnsupportedEncodingException{
		// TODO Auto-generated method stub
		Map<String,String> params = new HashMap<String,String>();
		payBackParams(params, request);
				
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
				
				/**
				 * 自定义业务逻辑代码块
				 */
				//1。钱包中加入付款金额
				//2。用户现金流水表记录充值信息  存入支付宝账号及交易方式
				//3.获取订单
				//4。1.根据订单购交易类型（购买，租赁）冻结款项或是扣款(新增用户消费记录，或更改用户钱包冻结款)
				//4.2.根据交易类型，更改订单状态
				//4.3.根据订单类型，生成发货单，另购买二手和会员申请无需发货单
				
				/**
				 * 获取当前时间
				 */
				String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
				
				orders = orderDao.getOrderByBatch(orderBatch);
				
				double price = 0.00;
				
				double rprice = 0.00;
				
				String couponCode = "";
				
				for(Order order: orders) {
					
					price += (order.getOrderPrice() * order.getOrderNum());
					
					String type = order.getOrderType();
					
					if (type.equals("R")) {
						rprice += (order.getOrderPrice() * order.getOrderNum());
					}
					
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
					price -= myCoupon.getCouponAmount();
				}
				
				myWallet = myWalletDao.getMyWallet(userCode);
				
				/**
				 * 通过钱包扣款
				 */
				double balance = myWallet.getWalletAmount() + Double.valueOf(total_fee) - price;
				
				myWallet.setWalletAmount(balance);
				myWallet.setWalletPledge(rprice);
				myWallet.setWalletUpdDate(now);
				
				myWalletDao.updateMyWallet(myWallet);
				
				/**
				 * 更改订单为支付状态
				 */
				orderDao.updateOrdersStatusToPay(orderBatch);
			}
			

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			System.out.print("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			System.out.print("fail");
		}
		
		return null;
	}



	@Transactional
	@Override
	public String payBackUpToVIP(HttpServletRequest request) throws UnsupportedEncodingException{
		// TODO Auto-generated method stub
		Map<String,String> params = new HashMap<String,String>();
		payBackParams(params, request);
		return null;
	}
	
	@Transactional
	@Override
	public String payBackBuyUsed(HttpServletRequest request) throws UnsupportedEncodingException{
		// TODO Auto-generated method stub
		Map<String,String> params = new HashMap<String,String>();
		payBackParams(params, request);
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private void payBackParams(Map<String,String> params, HttpServletRequest request) throws UnsupportedEncodingException{
		
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		orderBatch = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		
		trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		//商品名称

		subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
		
		//支付类型
		
		payment_type = new String(request.getParameter("payment_type").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易创建时间
		
		gmt_create = new String(request.getParameter("gmt_create").getBytes("ISO-8859-1"),"UTF-8");
		
		
		//交易付款时间
		
		gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易付款时间
		
		gmt_close = new String(request.getParameter("gmt_close").getBytes("ISO-8859-1"),"UTF-8");

		//交易金额
		
		total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		
		//是否扫码支付
		
		business_scene = new String(request.getParameter("business_scene").getBytes("ISO-8859-1"),"UTF-8");
		
		//userCode
		
		userCode = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");
	}

}
