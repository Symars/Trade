package com.syhorde.gametime.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syhorde.gametime.service.PayService;
import com.syhorde.gametime.util.DicCons;

@Controller
@Scope("prototype")
public class PayController {
	
	@Autowired
	private PayService payService;
	
	@RequestMapping(value = "/Alipay.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void payBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(payService.payBack(request));
		
	}
	
	@RequestMapping(value = "/AlipayUpToVIP.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void payBackUpToVIP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(payService.payBackUpToVIP(request));
		
	}
	
	@RequestMapping(value = "/AlipayBuyUsed.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void payBackBuyUsed(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(payService.payBackBuyUsed(request));
		
	}
}
