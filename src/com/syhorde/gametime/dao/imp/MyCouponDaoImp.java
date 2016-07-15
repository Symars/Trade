package com.syhorde.gametime.dao.imp;
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
	public  MyCoupon getMyCoupon(String userCode){
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.MyCouponDao.getMyCoupon", userCode);
	}
	

}
