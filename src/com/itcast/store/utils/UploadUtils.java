package com.itcast.store.utils;

import java.util.UUID;

public class UploadUtils {
	/**
	 * 鑾峰彇闅忔満鍚嶇О
	 * @param realName 鐪熷疄鍚嶇О
	 * @return uuid
	 */
	public static String getUUIDName(String realName){
		//realname  鍙兘鏄�  1.jpg   涔熷彲鑳芥槸  1
		//鑾峰彇鍚庣紑鍚�
		int index = realName.lastIndexOf(".");
		if(index==-1){
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}else{
			return UUID.randomUUID().toString().replace("-", "").toUpperCase()+realName.substring(index);
		}
	}
	
	/**
	 * 鑾峰彇鏂囦欢鐪熷疄鍚嶇О
	 * @param name
	 * @return
	 */
	public static String getRealName(String name){
		// c:/upload/1.jpg    1.jpg
		//鑾峰彇鏈�鍚庝竴涓�"/"
		int index = name.lastIndexOf("\\");
		return name.substring(index+1);
	}
	
	/**
	 * 鑾峰彇鏂囦欢鐩綍
	 * @param name 鏂囦欢鍚嶇О
	 * @return 鐩綍
	 */
	public static String getDir(String name){
		int i = name.hashCode();
		String hex = Integer.toHexString(i);
		int j=hex.length();
		for(int k=0;k<8-j;k++){
			hex="0"+hex;
		}
		return "/"+hex.charAt(0)+"/"+hex.charAt(1);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//String s="G:\\day17-鍩虹鍔犲己\\resource\\1.jpg";
		String s="1.jgp";
		String realName = getRealName(s);
		//System.out.println(realName);
		
		String uuidName = getUUIDName(realName);
		//System.out.println(uuidName);
		
		String dir = getDir(realName);
		System.out.println(dir);
		
		
	}
}
