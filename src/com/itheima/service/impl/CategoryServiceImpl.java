package com.itheima.service.impl;

import java.util.List;

import com.itheima.constant.Constant;
import com.itheima.dao.CategoryDao;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.JedisUtils;
import com.itheima.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {

	@Override
	//查询所有分类
	public String findAll() throws Exception {
		//1. 调用dao查询所有分类
		CategoryDao cd=new CategoryDaoImpl();
		List<Category> list =cd.findAll();
		//2.将List转换为json字符串
				if(list!=null && list.size()>0){
					//return JsonUtil.list2json(list);
				return JsonUtil.list2json(list);
				}
				return null;
	}

	/*@Override
	public String findAllFromRedis() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/

}
