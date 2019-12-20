package com.itcast.store.domain;

public class CartItem {
	private Product product;
	private int num;
	private double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSubTotal() {
		return product.getShop_price()*num;
	}
	public CartItem(Product product, int num, double subTotal) {
		super();
		this.product = product;
		this.num = num;
		this.subTotal = subTotal;
	}
	public CartItem() {
		super();
	}
}
