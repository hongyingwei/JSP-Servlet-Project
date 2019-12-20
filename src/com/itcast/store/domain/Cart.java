package com.itcast.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private double total=0;
	Map<String,CartItem> map=new HashMap<String,CartItem>();

	public void addCartItemToCar(CartItem cartItem){
		String pid=cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem oldItem=map.get(pid);
			oldItem.setNum(oldItem.getNum()+cartItem.getNum());
			
		}else{
			map.put(pid, cartItem);
		}
	}
	
	//取出map里面的CartItem的值
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	

	public void removeCartItem(String pid){
		map.remove(pid);
	}

	public void clearCart(){
		map.clear();
	}
	

	public double getTotal() {

		total=0;

		Collection<CartItem> values = map.values();

		for (CartItem cartItem : values) {
			total+=cartItem.getSubTotal();
		}
		
		return total;
	}
	

	public void setTotal(double total) {
		this.total = total;
	}

	public Map<String, CartItem> getMap() {
		return map;
	}


	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
}
