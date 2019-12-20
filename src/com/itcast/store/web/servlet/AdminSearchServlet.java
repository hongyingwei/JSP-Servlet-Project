package com.itcast.store.web.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.itcast.store.domain.Order;
import com.itcast.store.domain.Product;
import com.itcast.store.service.OrderService;
import com.itcast.store.service.ProductService;
import com.itcast.store.service.serivceImp.OrderServiceImp;
import com.itcast.store.service.serivceImp.ProductServiceImp;
import com.itcast.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class adminSearchServlet
 */
public class AdminSearchServlet extends BaseServlet {
	//商品查询
	public String searchProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pname = request.getParameter("pname");
		String cid = request.getParameter("cid");
		String pflag = request.getParameter("pflag");
		String is_hot = request.getParameter("is_hot");
		ProductService service = new ProductServiceImp();
		List<Product> list = service.searchProduct(pname, cid, pflag, is_hot);
		request.setAttribute("product", list);
		return "/admin/search/searchProduct.jsp";
	}
	//订单查询
	public String searchOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String value=request.getParameter("value");
		OrderService service = new OrderServiceImp();
		List<Order> list = service.searchProduct(value);
		request.setAttribute("allOrder", list);
		return "/admin/search/searchOrder.jsp";
	}
	//editOrderStateByOid
	public String editOrderStateByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");
		try {
			OrderService service = new OrderServiceImp();
			service.editOrderState(oid, 4);
			response.getWriter().print(1);
		} catch (Exception e) {
			response.getWriter().print(0);
			e.printStackTrace();
		}
		return null;
	}
	//查询每一个类别的商品销量
	public void searchCountByCid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderService service = new OrderServiceImp();
		List<Map<String, Object>> list = service.searchCountByCid();
		String json = JSONArray.fromObject(list).toString();
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	}
}
