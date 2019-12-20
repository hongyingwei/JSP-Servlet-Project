package com.itcast.store.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.itcast.store.domain.User;

/**
 * Servlet Filter implementation class PriviledgeFilter
 */
public class PriviledgeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		User user = (User) request.getSession().getAttribute("loginUser");
		if(user!=null) {
			//如果存在，放行
			chain.doFilter(request, response);
		}else {
			request.setAttribute("msg", "请先登录用户");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
