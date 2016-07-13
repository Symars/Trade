package com.syhorde.gametime.dao.imp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.BookingDao;
import com.syhorde.gametime.vo.Booking;

@Repository("bookingDao")
public class BookingDaoImp implements BookingDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Booking> getBooking(Map<String, Object> params) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectList("com.syhorde.gametime.dao.BookingDao.getBooking", params);
	}

	@Override
	public void gnrtBooking(List<Booking> bookings) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.BookingDao.gnrtBooking", bookings);
		session.commit();
	}

	@Override
	public double getProductPrice(Map<String, Object> params) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.BookingDao.getProductPrice", params);
	}

	@Override
	public List<String> getGoodsCode(Map<String, Object> params) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectList("com.syhorde.gametime.dao.BookingDao.getGoodsCode", params);
	}

	@Override
	public void lockGoods(List<String> goodsCodes) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.BookingDao.updateGoods", goodsCodes);
		session.commit();
	}

}
