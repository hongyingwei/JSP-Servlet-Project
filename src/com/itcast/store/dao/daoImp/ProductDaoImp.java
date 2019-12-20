package com.itcast.store.dao.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itcast.store.dao.ProductDao;
import com.itcast.store.domain.Product;
import com.itcast.store.utils.JDBCUtils;
import com.itcast.store.utils.Textempty;

public class ProductDaoImp implements ProductDao {

	@Override
	//查找热门商品信息
	public List<Product> findHots() throws Exception {
		String sql = "select *from product where pflag=0 and is_hot=1 order by pdate desc limit 0 , 9";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	//查找新品商品信息
	public List<Product> findNews() throws Exception {
		String sql = "select *from product where pflag=0 order by pdate desc limit 0 , 9";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	//通过id查找某个商品
	public Product findProductByPid(String pid) throws Exception {
		String sql = "select *from product where pid = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class), pid);
	}

	@Override
	//查找某类商品的信息
	public int findTotalRecords(String cid) throws Exception {
		String sql = "select count(*) from product where cid = ? and pflag=0";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)runner.query(sql, new ScalarHandler(), cid);
		return num.intValue();
	}

	@Override
	//通过页数查找该页的商品信息
	public List findProductByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql = "select *from product where cid=? and pflag=0 limit ?, ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
	}

	@Override
	//查找商品 上架 总共的信息记录条数
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from product where pflag=0";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}
	//通过分页查找上架商品信息
	public List<Product> findAllUpProductsWithPage(int startIndex, int pageSize) throws Exception{
		String sql="SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT ?, ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}

	@Override
	//保存商品信息
	public void saveProduct(Product product) throws Exception {
		String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
		qr.update(sql,params);
	}

	@Override
	//通过id查找商品
	public Product getProductById(String pid) throws Exception {
		String sql = "select * from product where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	//商品下架、上架
	public void Down_UP_Product(String statu, String pid) throws Exception {
		String sql = "update product set pflag = ? where pid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,statu, pid);
	}
	//修改商品信息
	public void editProduct(Product product) throws Exception {
		String sql = "update product set pname=?, market_price=?, shop_price=?, pimage=?, pdate=?, is_hot=?"
				+ ", pdesc=?, pflag=?, cid=? where pid=?";
		Object[] params={product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),
				product.getPflag(),product.getCid(),product.getPid()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}

	@Override
	//统计下架商品总数
	public int findDownTotalRecords() throws Exception {
		String sql="select count(*) from product where pflag=1";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	@Override
	//查询下架的商品
	public List<Product> findPushDownProduct(int startIndex, int pageSize) throws Exception {
		String sql="SELECT * FROM product WHERE pflag=1 ORDER BY pdate DESC LIMIT ?, ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}

	@Override
	//全文搜索商品
	public List<Product> searchProducts(String result) throws Exception {
		String sql = "SELECT *FROM product WHERE pflag=0 AND pname LIKE '%"+result+"%'";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	//查找某类商品的总数
	public int findByCategoryTotalRecords(String cid) throws Exception {
		String sql = "select count(*) from product where cid=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)runner.query(sql, new ScalarHandler(), cid);
		return num.intValue();
	}

	@Override
	//查找某类商品的所有商品
	public List<Product> findProductsByCategory(String cid, int startIndex, int pageSize) throws Exception {
		String sql = "select *from product where cid=? limit ?, ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
	}

	@Override
	//通过商品名、商品类别、商品是否上架、商品是否是热门
	public List<Product> searchProduct(String pname, String cid, String pflag, String is_hot) throws Exception {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where 1=1";
		List<Object> list = new ArrayList<Object>();
		if(!Textempty.isEmpty(pname)) {  //通过商品名查询
			sql = sql + " and pname like ?";
			list.add("%" + pname + "%");
		}
		if(!Textempty.isEmpty(cid)) { //通过商品类别查询
			sql = sql + " and cid=?";
			list.add(cid);
		}
		if(!Textempty.isEmpty(pflag)) {  //通过商品是否上架查询
			int flag = Integer.parseInt(pflag);
			sql = sql + " and pflag=?";
			list.add(flag);
		}
		if(!Textempty.isEmpty(is_hot)) { //通过商品是否热门
			int hot = Integer.parseInt(is_hot);
			sql = sql + " and is_hot=?";
			list.add(hot);
		}
		return runner.query(sql, new BeanListHandler<Product>(Product.class), list.toArray());
	}
}
