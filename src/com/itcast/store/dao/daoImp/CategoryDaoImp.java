package com.itcast.store.dao.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itcast.store.dao.CategoryDao;
import com.itcast.store.domain.Category;
import com.itcast.store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public List<Category> getAllCats() throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select *from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	@Override
	public void addCategory(Category c) throws Exception {
		String sql="insert into category values (? ,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCid(),c.getCname());
	}

	@Override
	public Category findCategoryByCid(String cid) throws Exception {
		String sql = "select  *from category where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Category category = qr.query(sql, new BeanHandler<Category>(Category.class), cid);
		return category;
	}

	@Override
	public void editCategory(Category category) throws Exception {
		String sql = "update category set cname=? where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql, category.getCname(),category.getCid());
	}

	@Override
	public void deleteCategory(String cid) throws Exception {
		String sql = "delete from category where cid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql, cid);
	}
}
