package com.syhorde.gametime.pay.alipay;

import java.util.HashMap;
import java.util.Map;

public class AlipayApi {
	
	public static String getUrl(String out_trade_no, String subject, String total_fee, String body, String userCode) {
		return AlipaySubmit.buildRequestUrl(pushParams(out_trade_no, subject, total_fee, body, userCode));
	}
	
	/**
	 * 
	 * @param out_trade_no	商户订单号，商户网站订单系统中唯一订单号，必填
	 * @param subject		订单名称，必填
	 * @param total_fee		付款金额，必填
	 * @param body			商品描述，可空
	 * @return
	 */
	private static Map<String, String> pushParams(String out_trade_no, String subject, String total_fee, String body, String userCode){
		
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
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
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
