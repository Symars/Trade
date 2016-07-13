package com.syhorde.gametime.dao.imp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.syhorde.gametime.dao.CategoryDao;
import com.syhorde.gametime.vo.Category;

@Repository("categoryDao")
public class CategoryDaoImp implements CategoryDao{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Category> getCategory() {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		return session.selectList("com.syhorde.gametime.dao.CategoryDao.getCategory");
	}

}
