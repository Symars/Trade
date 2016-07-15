package com.syhorde.gametime.dao.imp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.MyCouponDao;
import com.syhorde.gametime.vo.MyCoupon;

@Repository("myCouponDao")
public class MyCouponDaoImp implements MyCouponDao{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	@Override
	public  List<MyCoupon> getMyCoupon(String userCode){
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectList("com.syhorde.gametime.dao.MyCouponDao.getMyCoupon", userCode);
	}
	@Override
	public MyCoupon getMyCouponByCode(String myCouponCode) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.MyCouponDao.getMyCouponByCode", myCouponCode);
	}
	@Override
	public void updateMyCouponStatusByCode(String myCouponCode) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.MyCouponDao.updateMyCouponStatusByCode", myCouponCode);
	}
	@Override
	public MyCoupon getAllMyCouponByCode(String myCouponCode) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.MyCouponDao.getAllMyCouponByCode", myCouponCode);
	}	
}