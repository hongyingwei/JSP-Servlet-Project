package com.itcast.store.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 闅忔満鐢熸垚id
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
	public static String getUUID64(){
		return getId()+getId();
	}
	
	/**
	 * 鐢熸垚闅忔満鐮�
	 * @return
	 */
	public static String getCode(){
		return getId();
	}
	
	public static void main(String[] args) {
		System.out.println(getId());
	}
}
