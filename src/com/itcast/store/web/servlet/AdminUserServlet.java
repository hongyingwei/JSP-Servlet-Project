package com.itcast.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.User;
import com.itcast.store.service.userService;
import com.itcast.store.service.serivceImp.userServiceImp;
import com.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminUserServlet
 */
public class AdminUserServlet extends BaseServlet {
	//findAllUser()
	//分页查询信息
	public String findAllUserWithPath(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int currentPageNum = Integer.parseInt(request.getParameter("currentPageNum"));
		userService service = new userServiceImp();
		PageModel list = service.findAllUser(currentPageNum);
		request.setAttribute("page", list);
		return "/admin/user/list.jsp";
	}
	//userInformation
	public String userInformation(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String uid = request.getParameter("uid");
		userService service = new userServiceImp();
		User user = service.findUserById(uid);
		request.setAttribute("user", user);
		return "/admin/user/edit.jsp";
	}
}
