package com.itcast.store.web.servlet;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.store.domain.Cart;
import com.itcast.store.domain.CartItem;
import com.itcast.store.domain.Order;
import com.itcast.store.domain.OrderItem;
import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.User;
import com.itcast.store.service.OrderService;
import com.itcast.store.service.serivceImp.OrderServiceImp;
import com.itcast.store.utils.PaymentUtil;
import com.itcast.store.utils.UUIDUtils;
import com.itcast.store.web.base.BaseServlet;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	
	OrderService OrderService = new OrderServiceImp();
	
	// 保存订单到数据库
	public String saveOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = (User) req.getSession().getAttribute("loginUser");
		if (null == user) {
			req.setAttribute("msg", "请登录之后，再提交订单");
			return "/jsp/info.jsp";
		}
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		Order order = new Order();
		order.setOid(UUIDUtils.getCode());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setUser(user);
		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());

			orderItem.setOrder(order);
			order.getList().add(orderItem);
		}

		OrderService.saveOrder(order);
		cart.clearCart();
		req.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}

	// 查找某一页的待付款订单
	public String findMyOrdersWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = (User) req.getSession().getAttribute("loginUser");
		int curNum = Integer.parseInt(req.getParameter("num"));
		// SELECT * FROM orders WHERE uid=? limit ? , ?
		PageModel pm = OrderService.findMyOrdersWithPage(user, curNum);
		req.setAttribute("page", pm);
		return "/jsp/order_list.jsp";
	}
	//findPayOrdersWithPage
	//查找某一页待收货订单
	public String findPayOrdersWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = (User) req.getSession().getAttribute("loginUser");
		int curNum = Integer.parseInt(req.getParameter("num"));
		// SELECT * FROM orders WHERE uid=? limit ? , ?
		PageModel pm = OrderService.findPayOrdersWithPage(user, curNum);
		req.setAttribute("page", pm);
		return "/jsp/order_list.jsp";
	}
	// 通过订单编号查找某个订单
	/**
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String findOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String oid = req.getParameter("oid");
		Order order = OrderService.findOrderByOid(oid);
		req.setAttribute("order", order);
		return "/jsp/order_info.jsp";
	}

	// 删除订单
	public String deleteOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String oid = req.getParameter("oid");
		String currentPageNum = req.getParameter("num");
		OrderService.deleteOrderByOid(oid);
		resp.sendRedirect("/store/OrderServlet?method=findMyOrdersWithPage&num="+currentPageNum); // 重定向
		return null;
	}
	//完成签收
	public String completeOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String oid = req.getParameter("oid");
		String currentPageNum = req.getParameter("num");
		OrderService.getGoods(oid);
		resp.sendRedirect("/store/OrderServlet?method=findMyOrdersWithPage&num="+currentPageNum);
		return null;
	}
	// 实现支付功能
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String order_id = request.getParameter("oid");
		String order_address = request.getParameter("address");
		String order_name = request.getParameter("name");
		String order_telephone = request.getParameter("telephone");
		Order order = OrderService.findOrderByOid(order_id);
		order.setName(order_name);
		order.setTelephone(order_telephone);
		order.setAddress(order_address);
		OrderService.updateOrder(order);
		
		return "jsp/pay/alipay.trade.page.pay.jsp";
	}

	// 支付成功之后的数据响应
	public String callBack(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		String order_id = request.getParameter("oid");
		Order order = OrderService.findOrderByOid(order_id);
		if(order!=null) {
			order.setState(2);
			OrderService.updateOrder(order);
			request.setAttribute("msg", "付款成功，请到待收货查看自己的订单状态！");
		}else {
			request.setAttribute("msg", "付款失败");
		}
		
		return "/jsp/info.jsp";
	}
}
