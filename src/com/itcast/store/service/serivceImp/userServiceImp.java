package com.itcast.store.service.serivceImp;

import java.util.List;

import com.itcast.store.dao.userDao;
import com.itcast.store.dao.daoImp.userDaoImp;
import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.Product;
import com.itcast.store.domain.User;
import com.itcast.store.service.userService;

public class userServiceImp implements userService {

	@Override
	public void userRegist(User user) throws Exception {
		userDao userDao = new userDaoImp();
		userDao.userRegist(user);
	}

	@Override
	public boolean userActive(String code) throws Exception {
		userDao userDao = new userDaoImp();
		User user = userDao.userActive(code);
		if(user!=null) {
			user.setState(1);
			user.setCode(null);
			userDao.updateUser(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User userLogin(String username, String password) throws Exception {
		userDao userDao = new userDaoImp();
		User user = userDao.userLogin(username);
		if(user==null) {
			throw new RuntimeException("用户名不存在！");
		}else if(!user.getPassword().equals(password)) {
			throw new RuntimeException("密码错误！");
		}
		else if(user.getState()==0) {
			throw new RuntimeException("用户未激活！");
		}else {
			return user;
		}
	}

	@Override
	public boolean checkUserName(String username) throws Exception {
		userDao userDao = new userDaoImp();
		return userDao.checkUserName(username);
	}

	@Override
	public PageModel findAllUser(int currentPageNum) throws Exception {
		userDao userDao = new userDaoImp();
		int totalRecords = userDao.findUserTotalRecords();
		PageModel pm = new PageModel(currentPageNum, totalRecords, 8);
		// 关联集合 select * from user limit ? , ?
		List<User> list = userDao.findAllUserWithPage(pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 关联url
		pm.setUrl("AdminUserServlet?method=findAllUserWithPath");
		return pm;
	}

	@Override
	public User findUserById(String uid) throws Exception {
		userDao userDao = new userDaoImp();
		return userDao.findUserById(uid);
	}

	@Override
	public void changeUser(User user) throws Exception {
		userDao userDao = new userDaoImp();
		userDao.updateUser(user);
	}
 
}
