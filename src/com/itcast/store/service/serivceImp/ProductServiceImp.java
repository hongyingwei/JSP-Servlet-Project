package com.itcast.store.service.serivceImp;

import java.util.List;

import com.itcast.store.dao.ProductDao;
import com.itcast.store.dao.daoImp.ProductDaoImp;
import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.Product;
import com.itcast.store.service.ProductService;

public class ProductServiceImp implements ProductService {
	ProductDao dao = new ProductDaoImp();

	@Override
	public List<Product> findHots() throws Exception {
		return dao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		return dao.findNews();
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		return dao.findProductByPid(pid);
	}

	@Override
	public PageModel findProductByCidWithPage(String cid, int curNum) throws Exception {
		int totalRecords = dao.findTotalRecords(cid);
		PageModel pm = new PageModel(curNum, totalRecords, 18);
		List<Product> list = dao.findProductByCidWithPage(cid, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		pm.setUrl("ProductServlet?method=findProductByCidWithPage&cid=" + cid);
		return pm;
	}

	public PageModel findAllUpProductsWithPage(int curNum) throws Exception {
		// 1_创建对象
		int totalRecords = dao.findTotalRecords();
		PageModel pm = new PageModel(curNum, totalRecords, 16);
		// 2_关联集合 select * from product limit ? , ?
		List<Product> list = dao.findAllUpProductsWithPage(pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 3_关联url
		pm.setUrl("AdminProductServlet?method=findAllUpProductsWithPage");
		return pm;
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		dao.saveProduct(product);
	}

	@Override
	public Product getProductById(String pid) throws Exception {
		return dao.getProductById(pid);
	}

	@Override
	public void DownProduct(String statu, String pid) throws Exception {
		dao.Down_UP_Product(statu, pid);
	}

	@Override
	public void editProduct(Product product) throws Exception {
		dao.editProduct(product);
	}

	public PageModel findPushDownProduct(int curNum) throws Exception {
		// 1_创建对象
		int totalRecords = dao.findDownTotalRecords();
		PageModel pm = new PageModel(curNum, totalRecords, 16);
		// 2_关联集合 select * from product limit ? , ?
		List<Product> list = dao.findPushDownProduct(pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 3_关联url
		pm.setUrl("AdminProductServlet?method=findPushDownProduct");
		return pm;
	}

	@Override
	public List<Product> searchProducts(String result) throws Exception {
		return dao.searchProducts(result);
	}

	@Override
	public PageModel findProductsByCategory(String cid, int currentPageNum) throws Exception {
		// 1_创建对象
		int totalRecords = dao.findByCategoryTotalRecords(cid);
		PageModel pm = new PageModel(currentPageNum, totalRecords, 8);
		// 2_关联集合 select * from product limit ? , ?
		List<Product> list = dao.findProductsByCategory(cid, pm.getStartIndex(), pm.getPageSize());
		pm.setList(list);
		// 3_关联url
		pm.setUrl("AdminProductServlet?method=findProductsByCategory&cid="+cid);
		return pm;
	}

	@Override
	public List<Product> searchProduct(String pname, String cid, String pflag, String is_hot) throws Exception {
		return dao.searchProduct(pname, cid, pflag, is_hot);
	}
}
