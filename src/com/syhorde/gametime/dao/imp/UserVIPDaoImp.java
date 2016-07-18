package com.syhorde.gametime.dao.imp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.UserVIPDao;
import com.syhorde.gametime.vo.UserVIP;

@Repository("userVIPDao")
public class UserVIPDaoImp implements UserVIPDao{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public int getUserVIP(String userCode) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.UserVIPDao.getUserVIP", userCode);
	}

	@Override
	public UserVIP getUserVIPInfo(String userCode) {
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.UserVIPDao.getUserVIPInfo", userCode);
	}
	
	@Override
	public int getCurrentRentOrder(String userCode) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.UserVIPDao.getCurrentRentOrder", userCode);
	}

	@Override
	public void insertUserVIP(UserVIP userVIP) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.syhorde.gametime.dao.UserVIPDao.insertUserVIP", userVIP);
	}


}
