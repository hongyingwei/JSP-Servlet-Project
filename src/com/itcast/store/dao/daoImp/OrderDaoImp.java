package com.itcast.store.dao.daoImp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itcast.store.dao.OrderDao;
import com.itcast.store.domain.Order;
import com.itcast.store.domain.OrderItem;
import com.itcast.store.domain.Product;
import com.itcast.store.domain.User;
import com.itcast.store.domain.payGoods;
import com.itcast.store.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	//修改某个订单
	public void updateOrder(Order order) throws Exception {
		String sql="UPDATE orders SET ordertime=? ,total=? ,state= ?, address=?,NAME=?, telephone =? WHERE oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		qr.update(sql,params);
		
	}

	@Override
	//分页查询订单
	public List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql="select * from orders where uid=? and state=1 limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);
		
		for (Order order : list) {
			String oid=order.getOid();
			sql="select * from orderItem o ,product p where o.pid=p.pid and oid=?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
			for (Map<String, Object> map : list02) {
				OrderItem orderItem=new OrderItem();
				Product product=new Product();
				DateConverter dt = new DateConverter();
				dt.setPattern("yyyy-MM-dd");
				ConvertUtils.register(dt, java.util.Date.class);
				
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				
				orderItem.setProduct(product);
				order.getList().add(orderItem);
				
			}
		}
		return list;
	}

	@Override
	//通过订单号查询某个订单
	public Order findOrderByOid(String oid) throws Exception {
		String sql="select * from orders where oid= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		
		sql="select * from orderitem o, product p where o.pid=p.pid and oid=?";
		List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
		for (Map<String, Object> map : list02) {
			OrderItem orderItem=new OrderItem();
			Product product=new Product();
			DateConverter dt = new DateConverter();
			dt.setPattern("yyyy-MM-dd");
			ConvertUtils.register(dt, java.util.Date.class);
			
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map);
			
			orderItem.setProduct(product);
			order.getList().add(orderItem);
		}
		return order;
	}

	@Override
	//查询某用户的订单总数
	public int getTotalRecords(User user) throws Exception {
		String sql="select count(*) from orders where uid=? and state=1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	@Override
	//保存订单
	public void saveOrder(Connection conn, Order order) throws Exception {
		String sql="INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner();
		Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
		qr.update(conn,sql,params);
	}

	@Override
	//保存订单项
	public void saveOrderItem(Connection conn, OrderItem item) throws Exception {
		String sql="INSERT INTO orderitem VALUES(?,?,?,?,?)";
		QueryRunner qr=new QueryRunner();
		Object[] params={item.getItemid(),item.getQuantity(),item.getTotal(),item.getProduct().getPid(),item.getOrder().getOid()};
		qr.update(conn,sql,params);
	}
	//通过订单号删除订单
	public void deleteOrderByOid(String oid) throws Exception {
		String sql1="delete from orderitem where oid=?";
		String sql2="delete from orders where oid=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		runner.update(sql1, oid);
		runner.update(sql2, oid);
	}

	
	//通过订单状态查询这种状态的所有订单
	public List<Order> findOrdersByState(int state) throws Exception {
		String sql = "select *from orders where state=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Order>(Order.class), state);
	}

	
	//查找所有未签收订单
	public List<Order> findAllOrders() throws Exception {
		String sql = "select *from orders where state>1";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Order>(Order.class));
	}

	@Override
	//修改订单的状态
	public void editOrderState(String oid, int state) throws Exception {
		String sql="update orders set state = ? where oid=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		runner.update(sql, state, oid);
	}

	@Override
	//查询所有签收订单
	public List<Order> completeOrders() throws Exception {
		String sql = "select *from pay_shop";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		List<payGoods> list = runner.query(sql, new BeanListHandler<payGoods>(payGoods.class));
		List<Order> list01 = new ArrayList<>();
		for(payGoods pay: list) {
			Order o = new Order();
			o.setOid(pay.getOid());
			o.setOrdertime(pay.getOrdertime());
			o.setTotal(pay.getTotal());
			o.setState(pay.getState());
			o.setUser(pay.getUser());
			list01.add(o);
		}
		return list01;
	}

	@Override
	//把完成交易的订单放到pay_shop
	public void getGoods(String oid) throws Exception {
		String sql = "select *from orders where oid = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] obj = runner.query(sql, new ArrayHandler(), oid);
		String sql1 = "insert into pay_shop values(?,?,?,?,?)";
		runner.update(sql1, obj[0], obj[1], obj[2],Integer.parseInt(obj[3].toString())+1,obj[7]);
	}

	@Override
	//查询已付款订单的总数
	public int findOrderTotalRecords() throws Exception {
		String sql = "select count(*) from orders where state>1";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	@Override
	//分页查询已付款的订单
	public List<Order> findAllOrdersWithPage(int startIndex, int pageSize) throws Exception {
		String sql = "select *from orders where state>1 limit ? , ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Order>(Order.class), startIndex, pageSize);
	}

	@Override
	//通过订单的状态查询它的总数
	public int findOrderTotalRecords(int state) throws Exception {
		String sql = "select count(*) from orders where state=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long count = (Long)runner.query(sql, new ScalarHandler(), state);
		return count.intValue();
	}

	@Override
	//通过订单的状态分页查询它的信息
	public List<Order> findAllOrdersWithPageByState(int state, int startIndex, int pageSize) throws Exception {
		String sql = "select *from orders where state=? limit ? , ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Order>(Order.class), state, startIndex, pageSize);
	}

	@Override
	//查询完成交易表里面的总记录条数
	public int findCompleteTotalRecords() throws Exception {
		String sql = "select count(*) from pay_shop";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	@Override
	//分页查询完成交易的记录
	public List<payGoods> findAllCompleteOrdersWithPage(int startIndex, int pageSize) throws Exception {
		String sql = "select *from pay_shop limit ? , ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<payGoods>(payGoods.class), startIndex, pageSize);
	}

	@Override
	//查询已付款的某个人的所有订单总数
	public int getPayTotalRecords(User user) throws Exception {
		String sql="select count(*) from orders where uid=? and state<>1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	@Override
	//分页查询某个人的所有订单详情
	public List<Order> findPayOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql="select * from orders where uid=? and state<>1 limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);
		
		for (Order order : list) {
			String oid=order.getOid();
			sql="select * from orderItem o ,product p where o.pid=p.pid and oid=?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
			for (Map<String, Object> map : list02) {
				OrderItem orderItem=new OrderItem();
				Product product=new Product();
				
				DateConverter dt = new DateConverter();
				dt.setPattern("yyyy-MM-dd");
				ConvertUtils.register(dt, java.util.Date.class);
				
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				
				orderItem.setProduct(product);
				order.getList().add(orderItem);
				
			}
		}
		return list;
	}

	@Override
	//搜索某人或某订单的订单详情
	public List<Order> searchProduct(String value) throws Exception {
		String sql="select * from orders where state<>1 and oid=? or name=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),value, value);
		
		for (Order order : list) {
			String oid=order.getOid();
			sql="select * from orderItem o ,product p where o.pid=p.pid and oid=?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
			for (Map<String, Object> map : list02) {
				OrderItem orderItem=new OrderItem();
				Product product=new Product();
				
				DateConverter dt = new DateConverter();
				dt.setPattern("yyyy-MM-dd");
				ConvertUtils.register(dt, java.util.Date.class);
				
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				
				orderItem.setProduct(product);
				order.getList().add(orderItem);
				
			}
		}
		return list;
	}

	@Override
	//查询每一类商品的销售量
	public List<Map<String, Object>> searchCountByCid() throws Exception {
		String sql="SELECT SUM(o.quantity) quantity,c.cname FROM orderitem o " + 
				"INNER JOIN product p ON o.pid = p.pid " + 
				"INNER JOIN category c ON c.cid = p.cid " + 
				"WHERE p.pflag = 0 " + 
				"GROUP BY p.cid";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
		return list;
	}
}