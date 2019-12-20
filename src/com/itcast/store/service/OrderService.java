package com.itcast.store.service;

import java.util.List;
import java.util.Map;

import com.itcast.store.domain.Order;
import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.User;

public interface OrderService {

	void saveOrder(Order order)throws Exception;

	PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	void deleteOrderByOid(String oid) throws Exception;

	PageModel findOrdersByState(int state, int currentPageNum) throws Exception;

	void editOrderState(String oid, int state) throws Exception;

	PageModel completeOrders(int currentPageNum) throws Exception;

	void getGoods(String oid) throws Exception;

	PageModel findAllOrders(int currentPageNum) throws Exception;

	PageModel findunfillOrdersByState(int state, int currentPageNum) throws Exception;

	PageModel findPayOrdersWithPage(User user, int curNum) throws Exception;

	List<Order> searchProduct(String value) throws Exception;

	List<Map<String, Object>> searchCountByCid() throws Exception;
}
