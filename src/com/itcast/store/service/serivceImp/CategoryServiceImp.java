package com.itcast.store.service.serivceImp;

import java.util.List;

import com.itcast.store.dao.CategoryDao;
import com.itcast.store.dao.daoImp.CategoryDaoImp;
import com.itcast.store.domain.Category;
import com.itcast.store.service.CategoryService;

public class CategoryServiceImp implements CategoryService {

	@Override
	public List<Category> getAllCats() throws Exception {
		CategoryDao CategoryDao = new CategoryDaoImp();
		return CategoryDao.getAllCats();
	}

	@Override
	public void addCategory(Category c) throws Exception {
		CategoryDao CategoryDao = new CategoryDaoImp();
		CategoryDao.addCategory(c);
	}

	@Override
	public Category findCategoryByCid(String cid) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImp();
		return categoryDao.findCategoryByCid(cid);
	}

	@Override
	public void editCategory(Category category) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImp();
		categoryDao.editCategory(category);
	}

	@Override
	public void deleteCategory(String cid) throws Exception {
		CategoryDao categoryDao = new CategoryDaoImp();
		categoryDao.deleteCategory(cid);
	}

}
