package com.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.store.domain.Order;
import com.itcast.store.domain.PageModel;
import com.itcast.store.service.OrderService;
import com.itcast.store.service.serivceImp.OrderServiceImp;
import com.itcast.store.web.base.BaseServlet;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet {
	// 已付款订单state=2
	public String PayOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int currentPageNum = Integer.parseInt(req.getParameter("currentPageNum"));
		OrderService orderService = new OrderServiceImp();
		PageModel list = orderService.findAllOrders(currentPageNum);
		req.setAttribute("page", list);
		req.setAttribute("flag", 1);
		return "/admin/order/list.jsp";
	}

	// 查看订单详情
	public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 服务端获取到订单ID,
		String oid = req.getParameter("id");
		// 查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
		OrderService OrderService = new OrderServiceImp();
		Order order = OrderService.findOrderByOid(oid);
		// 将返回的集合转换为JSON格式字符串,响应到客户端
		String jsonStr = JSONArray.fromObject(order.getList()).toString();
		// 响应到客户端
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println(jsonStr);
		return null;
	}

	// filledOrders
	// 查找已发货的订单state=4
	public String filledOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int state = Integer.parseInt(request.getParameter("state"));
		int currentPageNum = Integer.parseInt(request.getParameter("currentPageNum"));
		OrderService orderService = new OrderServiceImp();
		PageModel list = orderService.findOrdersByState(state, currentPageNum);
		request.setAttribute("page", list);
		request.setAttribute("flag", 3);
		return "/admin/order/list.jsp";
	}

	// 查询未发货订单state=2
	public String unfilledOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int state = Integer.parseInt(request.getParameter("state"));
		int currentPageNum = Integer.parseInt(request.getParameter("currentPageNum"));
		OrderService orderService = new OrderServiceImp();
		PageModel list = orderService.findunfillOrdersByState(state, currentPageNum);
		request.setAttribute("page", list);
		request.setAttribute("flag", 2);
		return "/admin/order/list.jsp";
	}

	// 查询已完成交易的订单state=5
	public String completeOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderService orderService = new OrderServiceImp();
		
		int currentPageNum = Integer.parseInt(request.getParameter("currentPageNum"));
		PageModel list = orderService.completeOrders(currentPageNum);
		request.setAttribute("page", list);
		request.setAttribute("flag", 4);
		return "/admin/order/list.jsp";
	}
	//editOrders
	//修改订单状态
	public String editOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid = request.getParameter("oid");
		OrderService orderService = new OrderServiceImp();
		int currentPageNum = Integer.parseInt(request.getParameter("currentPageNum"));
		orderService.editOrderState(oid, 4);
		response.sendRedirect("/store/AdminOrderServlet?method=unfilledOrders&state=2&currentPageNum="+currentPageNum);//重定向
		return null;
	}
}
