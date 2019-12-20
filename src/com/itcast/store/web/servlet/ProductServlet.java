package com.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.Product;
import com.itcast.store.service.ProductService;
import com.itcast.store.service.serivceImp.ProductServiceImp;
import com.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {

	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pid = request.getParameter("pid");
			ProductService service = new ProductServiceImp();
			Product list = service.findProductByPid(pid);
			request.setAttribute("product", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/jsp/product_info.jsp";
	}
	//findProductByCidWithPage
	public String findProductByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cid = request.getParameter("cid");
			int curNum = Integer.parseInt(request.getParameter("num"));
			ProductService ProductService = new ProductServiceImp();
			PageModel pm = ProductService.findProductByCidWithPage(cid, curNum);
			request.setAttribute("page", pm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/jsp/product_list.jsp";
	}
	//searchProducts
	public String searchProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = request.getParameter("result");
		ProductService ProductService = new ProductServiceImp();
		List<Product> list = ProductService.searchProducts(result);
		request.setAttribute("product", list);
		return "/jsp/search.jsp";
	}
}
