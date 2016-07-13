package com.syhorde.gametime.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syhorde.gametime.dao.UserVIPDao;
import com.syhorde.gametime.util.DicCons;

@Controller
public class TestController {
	
	@Autowired
	UserVIPDao userVIPDao;
	
	@RequestMapping(value = "/test.trade", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody  
	public void testRe(HttpServletRequest reqeust, HttpServletResponse response) throws IOException {
		Map<String, Object> resultMap= new HashMap<String, Object>();
		int i = userVIPDao.getUserVIP("123");
		System.out.println(i);
		resultMap.put(DicCons.RESULT_CODE, 100);
		resultMap.put(DicCons.RESULT_DESC, "数据加载成功");
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(gson.toJson(resultMap));
	}
}