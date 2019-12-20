package com.itcast.store.service.serivceImp;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itcast.store.dao.OrderDao;
import com.itcast.store.dao.daoImp.OrderDaoImp;
import com.itcast.store.domain.Order;
import com.itcast.store.domain.OrderItem;
import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.User;
import com.itcast.store.domain.payGoods;
import com.itcast.store.service.OrderService;
import com.itcast.store.utils.JDBCUtils;
	



public class OrderServiceImp implements OrderService {
	
	OrderDao orderDao=new OrderDaoImp();

	public void saveOrder(Order order) throws SQLException {
		/*try {
			JDBCUtils.startTransaction();
			OrderDao orderDao=new OrderDaoImp();
			orderDao.saveOrder(order);
			for(OrderItem item:order.getList()){
				orderDao.saveOrderItem(item);
			}
			JDBCUtils.commitAndClose();
		} catch (Exception e) {
			JDBCUtils.rollbackAndClose();
		}
		*/
		
		Connection conn=null;
		try {
			conn=JDBCUtils.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			
			orderDao.saveOrder(conn,order);
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn,item);	
			}
			//提交事务
			conn.commit();
		} catch (Exception e) {
			//事务回滚
			conn.rollback();
		}
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		//select count(*) from orders where uid=?
		int totalRecords=orderDao.getTotalRecords(user);
		PageModel pm=new PageModel(curNum, totalRecords, 3);
		//2select * from orders where uid=? limit ? ,?
		List<?> list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
		
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		orderDao.updateOrder(order);
	}

	@Override
	public void deleteOrderByOid(String oid) throws Exception {
		orderDao.deleteOrderByOid(oid);
	}


	@Override
	public void editOrderState(String oid, int state) throws Exception {
		orderDao.editOrderState(oid, state);
	}

	@Override
	public PageModel completeOrders(int currentPageNum) throws Exception {
		int totalRecords = orderDao.findCompleteTotalRecords();
		PageModel pm = new PageModel(currentPageNum, totalRecords, 8);
		List<payGoods> list = orderDao.findAllCompleteOrdersWithPage(pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 关联url
		pm.setUrl("AdminOrderServlet?method=completeOrders");
		return pm;
	}

	@Override
	public void getGoods(String oid) throws Exception {
		orderDao.getGoods(oid);
		orderDao.deleteOrderByOid(oid);
	}

	@Override
	public PageModel findAllOrders(int currentPageNum) throws Exception {
		int totalRecords = orderDao.findOrderTotalRecords();
		PageModel pm = new PageModel(currentPageNum, totalRecords, 8);
		List<Order> list = orderDao.findAllOrdersWithPage(pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 关联url
		pm.setUrl("AdminOrderServlet?method=PayOrders");
		return pm;
	}

	@Override
	public PageModel findOrdersByState(int state, int currentPageNum) throws Exception {
		int totalRecords = orderDao.findOrderTotalRecords(state);
		PageModel pm = new PageModel(currentPageNum, totalRecords, 8);
		List<Order> list = orderDao.findAllOrdersWithPageByState(state, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 关联url
		pm.setUrl("AdminOrderServlet?method=filledOrders&state=4");
		return pm;
	}

	@Override
	public PageModel findunfillOrdersByState(int state, int currentPageNum) throws Exception {
		int totalRecords = orderDao.findOrderTotalRecords(state);
		PageModel pm = new PageModel(currentPageNum, totalRecords, 8);
		List<Order> list = orderDao.findAllOrdersWithPageByState(state, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 关联url
		pm.setUrl("AdminOrderServlet?method=unfilledOrders&state=2");
		return pm;
	}

	@Override
	public PageModel findPayOrdersWithPage(User user, int curNum) throws Exception {
		//select count(*) from orders where uid=?
		int totalRecords=orderDao.getPayTotalRecords(user);
		PageModel pm=new PageModel(curNum, totalRecords, 3);
		//2select * from orders where uid=? limit ? ,?
		List<Order> list=orderDao.findPayOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		pm.setUrl("OrderServlet?method=findPayOrdersWithPage");
		return pm;
	}

	@Override
	public List<Order> searchProduct(String value) throws Exception {
		return orderDao.searchProduct(value);
	}

	@Override
	public List<Map<String, Object>> searchCountByCid() throws Exception {
		return orderDao.searchCountByCid();
	}
}