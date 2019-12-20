package com.itcast.store.utils;

public class Textempty {
	/***
	* 判断字符串是否为空，为空，返回为false
	* @param s
	* @return
	 */
	public static boolean isEmpty(String s) {
		return s==null || s.length()==0;
	}
}
