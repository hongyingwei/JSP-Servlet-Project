package com.itcast.store.service;

import java.util.List;

import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.User;

public interface userService {

	void userRegist(User user) throws Exception;
	boolean userActive(String code) throws Exception;
	User userLogin(String username, String password) throws Exception;
	boolean checkUserName(String username) throws Exception;
	PageModel findAllUser(int currentPageNum) throws Exception;
	User findUserById(String uid) throws Exception;
	void changeUser(User user) throws Exception;
}
