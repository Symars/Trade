package com.syhorde.gametime.service.imp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syhorde.gametime.dao.MyCashDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.service.MyCashService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.vo.MyCash;

@Service("myCashService")
public class MyCashServiceImp implements MyCashService{
	@Autowired
	private  MyCashDao myCashDao;
	private  MyCash  myCash;
	@Override
	public String getMyCash(HttpServletRequest request){
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){

			String userCode = request.getParameter(DicCons.USER_CODE);
			myCash=myCashDao.getMyCash(userCode);
			if(myCash == null) {
				resultMap.put(DicCons.RESULT_CODE, 110);
				resultMap.put(DicCons.RESULT_DESC, "您没有现金");
				
				return JsonBuilder.toJson(resultMap, callback);
			} else {
				resultMap.put("myCash", myCash);
				resultMap.put(DicCons.RESULT_CODE, 100);
				resultMap.put(DicCons.RESULT_DESC, "数据加载成功");
				
				return JsonBuilder.toJson(resultMap, callback);
			}
		} else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			return JsonBuilder.toJson(resultMap, callback);
		}
		
	}
	
	
}
