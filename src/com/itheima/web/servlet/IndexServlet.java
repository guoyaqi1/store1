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
 * ��ҳģ��
 */

public class IndexServlet extends baseServlet {
	private static final long serialVersionUID = 1L;

  
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.����ProductService��ѯ������Ʒ��������Ʒ
			ProductService ps = new ProductServiceImpl();
			List<Product> hotList=ps.findHot();//�鿴������Ʒ
			List<Product> newList=ps.findNew();//�鿴������Ʒ
			
			//2.������List������request��
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
		}
		
		
		return "/jsp/index.jsp";
	}
   

}
