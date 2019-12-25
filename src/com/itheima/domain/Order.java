package com.itheima.domain;
//����
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	/*
	 * CREATE TABLE `orders` (
		  `oid` varchar(32) NOT NULL,
		  `ordertime` datetime DEFAULT NULL,
		  `total` double DEFAULT NULL,
		  `state` int(11) DEFAULT NULL,
		  `address` varchar(30) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  `telephone` varchar(20) DEFAULT NULL,
		  `uid` varchar(32) DEFAULT NULL,
		  PRIMARY KEY (`oid`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
	
	
	
	private String oid;
	private Date ordertime;
	private Double total;
	
	private Integer state;//����״̬ 0:δ���� 	1:�ѷ���	2:�����	3.�ѷ���
	private String address;
	private String name;
	
	private String telephone;
	
	//��ʾ���������ĸ��û�
	private User user;
	
	//��ʾ��ǰ���������Ķ�����
	private List<OrderItem> items = new ArrayList<>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	

}
