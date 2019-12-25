package com.itheima.web.servlet;

import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.web.servlet.base.baseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.itheima.domain.Product;
/**
 * 首页模块
 */

public class IndexServlet extends baseServlet {
	private static final long serialVersionUID = 1L;

  
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用ProductService查询最新商品和热门商品
			ProductService ps = new ProductServiceImpl();
			List<Product> hotList=ps.findHot();//查看热门商品
			List<Product> newList=ps.findNew();//查看最新商品
			
			//2.将两个List都放入request域
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
		}
		
		
		return "/jsp/index.jsp";
	}
   

}
