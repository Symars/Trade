package com.syhorde.gametime.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syhorde.gametime.dao.CategoryDao;
import com.syhorde.gametime.service.CategoryService;
import com.syhorde.gametime.vo.Category;

@Service("categoryService")
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getCategory() {
		return categoryDao.getCategory();
	}
}
