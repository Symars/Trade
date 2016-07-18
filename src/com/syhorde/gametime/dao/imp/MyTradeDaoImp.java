package com.syhorde.gametime.dao.imp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.MyTradeDao;
import com.syhorde.gametime.vo.MyTrade;
@Repository("myTradeDao")
public class MyTradeDaoImp implements MyTradeDao{

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	@Override
	public MyTrade getMyTrade(String userCode){
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.MyTradeDao.getMyTrade", userCode);
	}
	@Override
	public void insertMyTrade(MyTrade mytrade){
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("com.syhorde.gametime.dao.MyTradeDao.insertMyTrade", mytrade);
	}
}
