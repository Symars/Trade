package com.syhorde.gametime.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.service.CategoryService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.vo.Category;

@Controller
@Scope("prototype")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	private List<Category> categorys;
	
	@RequestMapping(value = "/GI_GetCategory.aspx", method = {RequestMethod.GET,RequestMethod.POST})  
	@ResponseBody
	public void getCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String token = request.getParameter(DicCons.TOKEN_KEY);
		
		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "数据加载成功");
			categorys = categoryService.getCategory();
			resultMap.put("Category", categorys);
		} else {
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "WebKey错误");
		}

		String callback = (String) request.getParameter(DicCons.CALL_BACK);		
        response.setCharacterEncoding(DicCons.CHARACTER_ENCODE_DEFAULT);
        response.setContentType(DicCons.CONTENT_TYPE_DEFAULT);  
		response.getWriter().print(JsonBuilder.toJson(resultMap, callback));
	}
}
