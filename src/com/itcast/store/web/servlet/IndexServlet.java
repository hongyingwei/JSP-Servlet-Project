package com.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.store.domain.Product;
import com.itcast.store.domain.User;
import com.itcast.store.service.ProductService;
import com.itcast.store.service.serivceImp.ProductServiceImp;
import com.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProductService service = new ProductServiceImp();
			User user = (User)request.getSession().getAttribute("loginUser");
			List<Product> list01 = service.findHots();
			List<Product> list02 = service.findNews();
			request.setAttribute("product_Hots", list01);
			request.setAttribute("product_News", list02);
			request.setAttribute("loginUser", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//转发到真是首页
		return "/jsp/index.jsp";
	}
	
}
