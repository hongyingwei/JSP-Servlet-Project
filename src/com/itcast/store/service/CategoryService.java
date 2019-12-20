package com.itcast.store.service;

import java.util.List;

import com.itcast.store.domain.Category;

public interface CategoryService {

	List<Category> getAllCats() throws Exception;

	void addCategory(Category c) throws Exception;

	Category findCategoryByCid(String cid) throws Exception;

	void editCategory(Category category)throws Exception;

	void deleteCategory(String cid) throws Exception;
	
}
