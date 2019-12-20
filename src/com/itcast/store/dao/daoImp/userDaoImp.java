package com.itcast.store.dao.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itcast.store.dao.userDao;
import com.itcast.store.domain.User;
import com.itcast.store.utils.JDBCUtils;

public class userDaoImp implements userDao{

	@Override
	//用户注册
	public void userRegist(User user) throws Exception {
		String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName()
				, user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(),
				user.getState(), user.getCode()};
		runner.update(sql, params);
	}

	@Override
	//用户激活
	public User userActive(String code) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select *from user where code=?";
		return runner.query(sql, new BeanHandler<User>(User.class), code);
	}

	@Override
	//用户修改
	public void updateUser(User user) throws Exception{
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUsername(), user.getPassword(), user.getName()
				, user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(),
				user.getState(), user.getCode(), user.getUid()};
		
		String sql = "update user set username=?, password=?, name=?, email=?, telephone=?, birthday=?,"
				+ "sex=?, state=?, code=? where uid=?";
		runner.update(sql, params);
	}

	@Override
	//用户登录
	public User userLogin(String username) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select *from user where username=?";
		return runner.query(sql, new BeanHandler<User>(User.class), username);
	}

	@Override
	//检查改用户是否已经存在
	public boolean checkUserName(String username) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from user where username=?";
		Long result = (Long)runner.query(sql, new ScalarHandler(), username);
		return result>0;
	}

	@Override
	//查询用户的总数
	public int findUserTotalRecords() throws Exception {
		String sql = "select count(*) from user";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	@Override
	//分页查询用户记录
	public List<User> findAllUserWithPage(int startIndex, int pageSize) throws Exception {
		String sql = "select *from user limit ?, ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<User>(User.class), startIndex, pageSize);
	}

	@Override
	//通过用户id查询某个用户信息
	public User findUserById(String uid) throws Exception {
		String sql = "select *from user where uid = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), uid);
	}

}
