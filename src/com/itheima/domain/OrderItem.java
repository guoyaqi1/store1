package com.itheima.domain;
//订单项
public class OrderItem {
	/*
	 * CREATE TABLE `orderitem` (
		  `itemid` varchar(32) NOT NULL,
		  `count` int(11) DEFAULT NULL,
		  `subtotal` double DEFAULT NULL,
		  `pid` varchar(32) DEFAULT NULL,
		  `oid` varchar(32) DEFAULT NULL,
		  PRIMARY KEY (`itemid`),
		  KEY `fk_0001` (`pid`),
		  KEY `fk_0002` (`oid`),
		  CONSTRAINT `fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
		  CONSTRAINT `fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/
	
	
	private String itemid;
	private Integer count;
	private Double subtotal;
	
	//表示包含哪个商品
	private  Product product;
	
	//表示属于哪个订单
	private  Order order;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
