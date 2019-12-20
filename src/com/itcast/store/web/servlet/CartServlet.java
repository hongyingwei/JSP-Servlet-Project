package com.itcast.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.store.domain.Cart;
import com.itcast.store.domain.CartItem;
import com.itcast.store.domain.Product;
import com.itcast.store.service.ProductService;
import com.itcast.store.service.serivceImp.ProductServiceImp;
import com.itcast.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	
	
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		if(null==cart){
		  
			cart=new Cart();
			req.getSession().setAttribute("cart", cart);
		}
   		
		String pid=req.getParameter("pid");
		int num=Integer.parseInt(req.getParameter("quantity"));
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);	
		
		CartItem cartItem=new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);

	
		cart.addCartItemToCar(cartItem);
		
		
		resp.sendRedirect("/store/jsp/cart.jsp");
		//return "/jsp/cart.jsp";
		return  null;
	}
	
	//removeCartItem
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String pid=req.getParameter("pid");
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		cart.removeCartItem(pid);
		
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
	//clearCart
	public String CleryCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		
		cart.clearCart();
		
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
	}
}




