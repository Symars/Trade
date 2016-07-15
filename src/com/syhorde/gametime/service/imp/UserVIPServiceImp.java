package com.syhorde.gametime.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syhorde.gametime.dao.UserVIPDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.service.UserVIPService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.util.StringUtil;
import com.syhorde.gametime.vo.UserVIP;

@Service("userVIPService")
public class UserVIPServiceImp implements UserVIPService {

	@Autowired
	private UserVIPDao userVIPDao;
	
	private UserVIP userVIP;
	
	@Override
	public String upToVIP(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			String userCode = request.getParameter(DicCons.USER_CODE);
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
			
			
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "数据加载成功");
			
			return JsonBuilder.toJson(resultMap, callback);
		}else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

}
