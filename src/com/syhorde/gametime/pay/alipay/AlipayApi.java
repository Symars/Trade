package com.syhorde.gametime.pay.alipay;

import java.util.HashMap;
import java.util.Map;

public class AlipayApi {
	
	/**
	 * 
	 * @param out_trade_no	订单号
	 * @param subject		名称
	 * @param total_fee		金额
	 * @param body			备注
	 * @param userCode		用户编码
	 * @param url			异步回调地址
	 * @return
	 */
	public static String getUrl(String out_trade_no, String subject, String total_fee, String body, String userCode, String url) {
		return AlipaySubmit.buildRequestUrl(pushParams(out_trade_no, subject, total_fee, body, userCode, url));
	}
	
	
	/**
	 * 
	 * @param out_trade_no	商户订单号，商户网站订单系统中唯一订单号，必填
	 * @param subject		订单名称，必填
	 * @param total_fee		付款金额，必填
	 * @param body			商品描述，可空
	 * @return
	 */
	private static Map<String, String> pushParams(String out_trade_no, String subject, String total_fee, String body, String userCode ,String url){
		
//		 //商户订单号，商户网站订单系统中唯一订单号，必填
//       String out_trade_no = "tes123qwdfasv23sdx";
//
//       //订单名称，必填
//       String subject = "tesgt12xcxv";
//
//       //付款金额，必填
//       String total_fee = "100.01";
//
//       //商品描述，可空
//       String body = "tesg12cvbvd";
       
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("extra_common_param", userCode);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		return sParaTemp;
	}
	
}
