package com.syhorde.gametime.service.imp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syhorde.gametime.dao.MyWalletDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.service.MyWalletService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.vo.MyWallet;
@Service("myWalletService")
public class MyWalletServiceImp implements MyWalletService{
	@Autowired
	private MyWalletDao myWalletDao;
	private MyWallet myWallet;
	@Override
	public String getMyWallet(HttpServletRequest request){
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			String userCode = request.getParameter(DicCons.USER_CODE);
			myWallet=myWalletDao.getMyWallet(userCode);
			if(myWallet == null) {
				resultMap.put(DicCons.RESULT_CODE, 110);
				resultMap.put(DicCons.RESULT_DESC, "您没有钱包");
				return JsonBuilder.toJson(resultMap, callback);
			} else {
				resultMap.put("MyWallet", myWallet);
				resultMap.put(DicCons.RESULT_CODE, 100);
				resultMap.put(DicCons.RESULT_DESC, "数据加载成功");
				
				return JsonBuilder.toJson(resultMap, callback);
			}
		}else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			return JsonBuilder.toJson(resultMap, callback);
		}

			
	}
}
