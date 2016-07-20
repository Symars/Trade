package com.syhorde.gametime.dao.imp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
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
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.syhorde.gametime.dao.OrderDao.gnrtOrder", orders);
		session.commit();
	}

	@Override
	public List<Order> getOrder(Map<String, Object> params) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectList("com.syhorde.gametime.dao.OrderDao.getOrder", params);
	}

	@Override
	public List<Order> getOrderByBatch(String batch) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectList("com.syhorde.gametime.dao.OrderDao.getOrderByBatch", batch);
	}
	
	@Override
	public Order getOrderByCode(String orderCode) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.OrderDao.getOrderByCode", orderCode);
	}

	@Override
	public void deleteOrder(String orderCode) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.delete("com.syhorde.gametime.dao.OrderDao.deleteOrder", orderCode);
		session.commit();
	}
	
	@Override
	public void deleteOrders(String batch) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.delete("com.syhorde.gametime.dao.OrderDao.deleteOrders", batch);
		session.commit();
	}

	@Override
	public void updateOrdersStatusToPay(String batch) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.OrderDao.updateOrdersStatus", batch);
		session.commit();
	}
	
	@Override
	public void updateOrdersStatusToFinish(String batch) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.OrderDao.updateOrdersStatusToFinish", batch);
		session.commit();
	}
	
	@Override
	public void updateOrderStatus(Map<String, Object> params) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.OrderDao.updateOrderStatus", params);
		session.commit();
	}

	@Override
	public void updateOrdersToBuyUsed(String batch) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.OrderDao.updateOrdersToBuyUsed", batch);
		session.commit();
	}

}
