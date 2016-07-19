package com.syhorde.gametime.service.imp;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.syhorde.gametime.dao.BuyUsedDao;
import com.syhorde.gametime.dao.MyWalletDao;
import com.syhorde.gametime.dao.OrderDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.service.BuyUsedService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DateUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.vo.MyWallet;
import com.syhorde.gametime.vo.Order;

public class BuyUsedServiceImp implements BuyUsedService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BuyUsedDao buyUsedDao;
	@Autowired
	private MyWalletDao myWalletDao;
	
	private MyWallet myWallet;
	
	private Order order;
	
	@Transactional
	@Override
	public String buyUsed(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			String userCode = request.getParameter(DicCons.USER_CODE);
			
			String orderCode = request.getParameter("OrderCode");
			
			order = orderDao.getOrderByCode(orderCode);

			LocalDateTime now = LocalDateTime.now();
			
			if("R".equals(order.getOrderStatus()) && userCode.equals(order.getUserCode())) {
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("ProductCode", order.getProductCode());
				params.put("ProductItemCode", order.getProductItemCode());
				
				double perRentPrice = buyUsedDao.getRentPrice(params);
				
				LocalDateTime startDate = LocalDateTime.parse(order.getOrderStartDate());

				int days = DateUtil.diffDateTime(startDate, now);
						
				/**
				 * 租金
				 */
				double rentPrice = perRentPrice * days;
				
				
				
			} else {
				
				resultMap.put(DicCons.RESULT_CODE, 110);
				resultMap.put(DicCons.RESULT_DESC, "订单不具备二手购买条件");
				return JsonBuilder.toJson(resultMap, callback);
			}
			
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "会员申请成功");
			return JsonBuilder.toJson(resultMap, callback);
		}else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

}
