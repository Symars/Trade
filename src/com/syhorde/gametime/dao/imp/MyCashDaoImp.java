package com.syhorde.gametime.dao.imp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.MyCashDao;
import com.syhorde.gametime.vo.MyCash;

@Repository("myCashDao")
public class MyCashDaoImp implements MyCashDao{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	@Override
	public MyCash getMyCash(String userCode){
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.MyCashDao.getMyCash", userCode);
	}
	@Override
	public void insertMyCash(MyCash mycash){
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.syhorde.gametime.dao.MyCashDao.insertMyCash", mycash);
	}

}
