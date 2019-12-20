package com.itcast.store.service;

import java.util.List;

import com.itcast.store.domain.PageModel;
import com.itcast.store.domain.Product;

public interface ProductService {

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

	Product findProductByPid(String pid) throws Exception;

	PageModel findProductByCidWithPage(String cid, int curNum) throws Exception;

	PageModel findAllUpProductsWithPage(int curNum)throws Exception;

	void saveProduct(Product product) throws Exception;

	Product getProductById(String pid) throws Exception;

	void DownProduct(String statu, String pid)throws Exception;

	void editProduct(Product product)throws Exception;

	PageModel findPushDownProduct(int curNum) throws Exception;

	List<Product> searchProducts(String result) throws Exception;

	PageModel findProductsByCategory(String cid, int currentPageNum) throws Exception;

	List<Product> searchProduct(String pname, String cid, String pflag, String is_hot) throws Exception;

}
