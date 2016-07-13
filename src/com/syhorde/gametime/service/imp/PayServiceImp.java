package com.syhorde.gametime.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syhorde.gametime.dao.OrderDao;
import com.syhorde.gametime.pay.alipay.AlipayNotify;
import com.syhorde.gametime.service.PayService;

@Service("payService")
public class PayServiceImp implements PayService {
	
	@Autowired
	private OrderDao orderDao;

	@Transactional
	@SuppressWarnings("rawtypes")
	@Override
	public String payBack(HttpServletRequest request) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		Map<String,String> params = new HashMap<String,String>();
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

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		//商品名称

		String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
		
		//支付类型
		
		String payment_type = new String(request.getParameter("payment_type").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易创建时间
		
		String gmt_create = new String(request.getParameter("gmt_create").getBytes("ISO-8859-1"),"UTF-8");
		
		
		//交易付款时间
		
		String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易付款时间
		
		String gmt_close = new String(request.getParameter("gmt_close").getBytes("ISO-8859-1"),"UTF-8");

		//交易金额
		
		String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		
		//是否扫码支付
		
		String business_scene = new String(request.getParameter("business_scene").getBytes("ISO-8859-1"),"UTF-8");
		
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
				orderDao.updateOrderStatus(out_trade_no, "" );
			}
			

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			System.out.print("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			System.out.print("fail");
		}
		
		return null;
	}

}
