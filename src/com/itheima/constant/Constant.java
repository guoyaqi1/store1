package com.itheima.constant;

public interface Constant {

	
	int USER_IS_NOT_ACTIVE = 0;//用户未激活
	int USER_IS_ACTIVE = 1;//用户已激活
	
	String SAVE_NAME ="ok";//保存用户信息
	int REDIS_PORT = 6379;
	String REDIS_HOST = "192.168.17.136";
	
	int PRODUCT_IS_HOT = 1;//商品热门
	
	int PRODUCT_IS_UP = 0;//商品上架
	
	int PRODUCT_IS_DOWN = 1;//商品下架
	
	/*
	 * 订单状态
	 * */
	
    int ORDER_WEIFUKUAN=0;//未付款
	
	
	int ORDER_YIFUKUAN=1;//已付款
	
	
	int ORDER_YIFAHUO=2;//已发货
	
	
	int ORDER_YIWANCHENG=3;//已完成

}
