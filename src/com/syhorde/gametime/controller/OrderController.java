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

import com.syhorde.gametime.service.OrderService;
import com.syhorde.gametime.util.DicCons;

@Controller
@Scope("prototype")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	/**
	 * 生成Order
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/GI_GnrtOrder.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void gnrtOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(orderService.gnrtOrder(request));
		
	}
	
	/**
	 * 获取订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/GI_GetOrder.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void getOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(orderService.getOrder(request));
		
	}
	
	/**
	 * 删除订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/GI_DeleteOrder.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(orderService.deleteOrder(request));
		
	}
	
	/**
	 * 删除订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/GI_PayOrder.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void payOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(orderService.payOrder(request));
		
	}
}
