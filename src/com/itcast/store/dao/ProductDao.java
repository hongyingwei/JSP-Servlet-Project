package com.itcast.store.dao;

import java.util.List;

import com.itcast.store.domain.Product;

public interface ProductDao {

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

	Product findProductByPid(String pid) throws Exception;

	int findTotalRecords() throws Exception;

	List findProductByCidWithPage(String cid, int startIndex, int pageSize) throws Exception;

	List<Product> findAllUpProductsWithPage(int startIndex, int pageSize)throws Exception;

	int findTotalRecords(String cid) throws Exception;

	void saveProduct(Product product) throws Exception;

	Product getProductById(String pid)throws Exception;

	void Down_UP_Product(String statu, String pid)throws Exception;

	void editProduct(Product product)throws Exception;

	int findDownTotalRecords() throws Exception;

	List<Product> findPushDownProduct(int startIndex, int pageSize) throws Exception;

	List<Product> searchProducts(String result) throws Exception;

	int findByCategoryTotalRecords(String cid) throws Exception;

	List<Product> findProductsByCategory(String cid, int startIndex, int pageSize) throws Exception;

	List<Product> searchProduct(String pname, String cid, String pflag, String is_hot) throws Exception;
}
