package com.itheima.web.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.CategoryService;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.web.servlet.base.baseServlet;

/**
 * ǰ̨����ģ��
 */

public class CategoryServlet extends baseServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * ��ѯ���з���
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//0.������Ӧ�ı���
			response.setContentType("text/html;charset=utf-8");
			
			//1.����service,��ѯ���з��࣬����ֵjson�ַ���
			CategoryService cs = new CategoryServiceImpl();
			//
			String value = cs.findAll();
			
			//
			//String value = cs.findAllFromRedis();
			//2.���ַ���д�������
			response.getWriter().println(value);
		} catch (Exception e) {
		}
		return null;
	}
	
   

}
