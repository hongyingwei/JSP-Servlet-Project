package com.itcast.store.dao;

import java.util.List;

import com.itcast.store.domain.User;

public interface userDao {
	void userRegist(User user) throws Exception;
	User userActive(String code) throws Exception;
	void updateUser(User user) throws Exception;
	User userLogin(String username) throws Exception;
	boolean checkUserName(String username) throws Exception;
	int findUserTotalRecords() throws Exception;
	List<User> findAllUserWithPage(int startIndex, int pageSize) throws Exception;
	User findUserById(String uid) throws Exception;
}
