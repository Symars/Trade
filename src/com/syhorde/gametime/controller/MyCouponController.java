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

import com.syhorde.gametime.service.MyCouponService;
import com.syhorde.gametime.util.DicCons;

@Controller
@Scope("prototype")
public class MyCouponController {
	@Autowired
	private MyCouponService myCouponService;
	
	@RequestMapping(value = "/GI_GetMyCoupon.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void getMyCoupon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(myCouponService.getMyCoupon(request));
	}
}
