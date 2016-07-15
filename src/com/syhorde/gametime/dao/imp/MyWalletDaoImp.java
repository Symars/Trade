package com.syhorde.gametime.dao.imp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.MyWalletDao;
import com.syhorde.gametime.vo.MyWallet;

@Repository("myWalletDao")
public class MyWalletDaoImp implements MyWalletDao{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	@Override
	public MyWallet getMyWallet(String userCode){
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectOne("com.syhorde.gametime.dao.MyWalletDao.getMyWallet", userCode);
	}
	@Override
	public void updateMyWallet(MyWallet myWallet) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		session.update("com.syhorde.gametime.dao.MyWalletDao.updateMyWallet", myWallet);
		session.commit();
	}
}
