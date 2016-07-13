package com.syhorde.gametime.dao.imp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.OrderDao;
import com.syhorde.gametime.vo.Order;

@Repository("orderDao")
public class OrderDaoImp implements OrderDao {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public void gnrtOrder(List<Order> orders) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Order> getOrder(Map<String, Object> params) {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrder(String orders) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrderStatus(String orderCode, String status) {
		// TODO Auto-generated method stub
		
	}

}
