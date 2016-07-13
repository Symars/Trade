package com.syhorde.gametime.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.syhorde.gametime.dao.BookingDao;
import com.syhorde.gametime.dao.UserVIPDao;
import com.syhorde.gametime.json.JsonBuilder;
import com.syhorde.gametime.service.BookingService;
import com.syhorde.gametime.util.CommonUtil;
import com.syhorde.gametime.util.DicCons;
import com.syhorde.gametime.util.GUID;
import com.syhorde.gametime.vo.Booking;

@Service("bookingService")
public class BookingServiceImp implements BookingService{
	
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private UserVIPDao userVIPDao;
	
	private List<Booking> bookings;

	private Booking booking;
	
	private int vip;
	
	private int currentRentOrder;
	
	@Transactional
	@Override
	public String gnrtBooking(HttpServletRequest request) {
		
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			String userCode = request.getParameter("userCode");
			
			/**
			 * 获取当前时间
			 */
			String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

			/**
			 * 获取用户身份级别 ，是否为会员，当期是否有租赁订单
			 */
			vip = userVIPDao.getUserVIP(userCode);
			currentRentOrder = userVIPDao.getCurrentRentOrder(userCode);
			
			/**
			 * 商品信息
			 */
			String bookingList = request.getParameter("List");
			
			Gson gson = new Gson();
			
			List<Map<String, Object>> bookingMaps = gson.fromJson(bookingList, new TypeToken<List<Map<String, Object>>>(){}.getType());
						
			
			/**
			 * 预订单批次码
			 */
			String batch = GUID.getUUID();
			
			int rentNo = 0;
			
			List<String> goodsCodes = new ArrayList<String>();
			
			if (vip > 0) {
				//会员租赁变为普通租赁
				if (currentRentOrder > 0) {
					resultMap.put(DicCons.RESULT_CODE, 110);
					resultMap.put(DicCons.RESULT_DESC, "存在正在租赁的游戏，无法再次租赁");
					return JsonBuilder.toJson(resultMap, callback);
				} else {
					//会员租赁
					for(Map<String, Object> map: bookingMaps) {
						
						/*
						 * 判断购买类型
						 * 若为租赁类型且身份为会员
						 * 判断该会员当前是否有租赁项目
						 * 若当前会员无租赁项目则设置 price = 0 ，且租赁类型产品数量只能为1，若租赁
						 * 若有，则返回  【当前有未完成的租赁交易，无法再次租赁】 状态
						 */
						
						/**
						 * 根据ProductCode，ProductItemCode获取商品信息
						 */
						String productCode = (String)map.get("ProductCode");
						String ProductItemCode = (String)map.get("ProductItemCode");
						int num = (Integer)map.get("Num");
						
						Map<String ,Object> params = new HashMap<String, Object>();
						
						params.put("ProductCode", productCode);
						params.put("ProductItemCode", ProductItemCode);
						params.put("num", num);

						double price = 0.00;
						
						List<String> goodsCode = bookingDao.getGoodsCode(params);
						
						if (goodsCode.size() < num) {
							resultMap.put(DicCons.RESULT_CODE, "120");
							resultMap.put(DicCons.RESULT_DESC, "商品" + productCode + "库存不足");
							return JsonBuilder.toJson(resultMap, callback);
						} else {
							goodsCodes.addAll(goodsCode);
						}
						
						String type = (String)map.get("Type");
						if ("R".equals(type)) {
							//租赁
							rentNo += num;
							if (rentNo > 1) {
								resultMap.put(DicCons.RESULT_CODE, 111);
								resultMap.put(DicCons.RESULT_DESC, "会员用户每次只能租赁一件商品");
								return JsonBuilder.toJson(resultMap, callback);
							} else {
								price = 0.00;
							}
						} else {
							price = bookingDao.getProductPrice(params);
						}
						
						booking = new Booking();
						
						booking.setBookingBatch(batch);
						booking.setBookingCode(GUID.getUUID());
						booking.setUserCode(userCode);
						booking.setBookingDate(now);
						booking.setBookingStartTime(now);
						booking.setBookingEndTime(now);
						booking.setProductCode(productCode);
						booking.setProductItemCode(ProductItemCode);
						booking.setBookingPrice(price);
						booking.setBookingNum(num);
						booking.setBookingStatus("0");
						booking.setBookingType(type);
						bookings.add(booking);
						
					}
				}
				
			} else {
				//普通租赁
				for(Map<String, Object> map: bookingMaps) {
					
					/*
					 * 判断购买类型
					 * 若为租赁类型且身份为会员
					 * 判断该会员当前是否有租赁项目
					 * 若当前会员无租赁项目则设置 price = 0 ，且租赁类型产品数量只能为1，若租赁
					 * 若有，则返回  【当前有未完成的租赁交易，无法再次租赁】 状态
					 */
					
					/**
					 * 根据ProductCode，ProductItemCode获取商品信息
					 */
					String productCode = (String)map.get("ProductCode");
					String ProductItemCode = (String)map.get("ProductItemCode");
					int num = (Integer)map.get("Num");
					
					Map<String ,Object> params = new HashMap<String, Object>();
					
					params.put("ProductCode", productCode);
					params.put("ProductItemCode", ProductItemCode);
					params.put("num", num);
					
					double price = bookingDao.getProductPrice(params);
					
					List<String> goodsCode = bookingDao.getGoodsCode(params);
					
					if (goodsCode.size() < num) {
						resultMap.put(DicCons.RESULT_CODE, "120");
						resultMap.put(DicCons.RESULT_DESC, "商品" + productCode + "库存不足");
						return JsonBuilder.toJson(resultMap, callback);
					} else {
						goodsCodes.addAll(goodsCode);
					}
					
					String type = (String)map.get("Type");
					
					booking = new Booking();
					
					booking.setBookingBatch(batch);
					booking.setBookingCode(GUID.getUUID());
					booking.setUserCode(userCode);
					booking.setBookingDate(now);
					booking.setBookingStartTime(now);
					booking.setBookingEndTime(now);
					booking.setProductCode(productCode);
					booking.setProductItemCode(ProductItemCode);
					booking.setBookingPrice(price);
					booking.setBookingNum(num);
					booking.setBookingStatus("0");
					booking.setBookingType(type);
					bookings.add(booking);
					
				}
			}
			
			bookingDao.gnrtBooking(bookings);
			
			bookingDao.lockGoods(goodsCodes);
			
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "数据添加成功");
			
			return JsonBuilder.toJson(resultMap, callback);
			
		} else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			
			return JsonBuilder.toJson(resultMap, callback);
		}
	}

	@Override
	public String getBooking(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = request.getParameter(DicCons.TOKEN_KEY);
		String callback = request.getParameter(DicCons.CALL_BACK);

		Map<String, Object> resultMap= new HashMap<String, Object>();
		
		if(CommonUtil.checkToken(token)){
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("userCode", request.getParameter("userCode"));
			params.put("date", LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ISO_DATE_TIME));
			params.put("batch", request.getParameter("BookingBatch"));
			
			bookings = bookingDao.getBooking(params);
			
			resultMap.put("List", bookings);
			
			resultMap.put(DicCons.RESULT_CODE, 100);
			resultMap.put(DicCons.RESULT_DESC, "数据加载成功");
			
			return JsonBuilder.toJson(resultMap, callback);
			
		} else {
			
			resultMap.put(DicCons.RESULT_CODE, 401);
			resultMap.put(DicCons.RESULT_DESC, "秘钥错误");
			
			return JsonBuilder.toJson(resultMap, callback);
		}
	}
	
}
