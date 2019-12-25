package com.itheima.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * id
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getCode(){
		return getId();
	}
	
	public static void main(String[] args) {
		System.out.println(getId());
		System.out.println(getCode());
	}
}
