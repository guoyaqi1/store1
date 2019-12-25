package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.PageBean;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	/**
	 * 保存订单
	 */
	public void save(Order order) throws Exception {
		try {
			//获取dao
			OrderDao od  = (OrderDao) BeanFactory.getBean("OrderDao");
			//1.开启事务
			DataSourceUtils.startTransaction();
			
			//2.向Orders表中插入一条
			od.save(order);
			
			//3.向Orderitem中插入n条
			for (OrderItem oi : order.getItems()) {
				od.saveItem(oi);
			}
			
			//4.事务控制
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
	}

	@Override
	public PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getById(String oid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Order order) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> findAllByState(String state) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
