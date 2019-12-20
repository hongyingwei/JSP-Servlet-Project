package com.itcast.store.dao;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.itcast.store.domain.Order;
import com.itcast.store.domain.OrderItem;
import com.itcast.store.domain.User;
import com.itcast.store.domain.payGoods;


public interface OrderDao {

	void saveOrder(Connection conn, Order order)throws Exception;

	void saveOrderItem(Connection conn, OrderItem item)throws Exception;

	int getTotalRecords(User user)throws Exception;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	void deleteOrderByOid(String oid)throws Exception;

	List<Order> findOrdersByState(int state) throws Exception;

	List<Order> findAllOrders() throws Exception;

	void editOrderState(String oid, int state) throws Exception;

	List<Order> completeOrders() throws Exception;

	void getGoods(String oid) throws Exception;

	int findOrderTotalRecords() throws Exception;

	int findOrderTotalRecords(int state) throws Exception;

	List<Order> findAllOrdersWithPageByState(int state, int startIndex, int pageSize) throws Exception;

	int findCompleteTotalRecords() throws Exception;

	List<payGoods> findAllCompleteOrdersWithPage(int startIndex, int pageSize) throws Exception;

	List<Order> findAllOrdersWithPage(int startIndex, int pageSize) throws Exception;

	int getPayTotalRecords(User user) throws Exception;

	List<Order> findPayOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;

	List<Order> searchProduct(String value) throws Exception;

	List<Map<String, Object>> searchCountByCid() throws Exception;

}
