package com.itheima.web.servlet;

import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.web.servlet.base.baseServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *��Ʒ����չʾ
 */

public class ProductServlet extends baseServlet {
	private static final long serialVersionUID = 1L;
	

		/**
		 * ��ҳ��Ʒչʾ
		 */
		public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			try {
				//1.��ȡpagenumber cid  ����pagesize
				/*String parameter = request.getParameter("pageNumber");*/
				int pageNumber = 1;
				
				try {
					pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				} catch (NumberFormatException e) {
				}
				
				int pageSize = 12;
				String cid = request.getParameter("cid");
				
				//2.����service ��ҳ��ѯ��Ʒ����:3��, ����ֵ:pagebean
				ProductService ps = new ProductServiceImpl();
				PageBean<Product> bean=ps.findByPage(pageNumber,pageSize,cid);
				
				//3.��pagebean����request��,����ת�� product_list.jsp
				request.setAttribute("pb", bean);
			} catch (Exception e) {
				request.setAttribute("msg", "��ҳ��ѯʧ��");
				return "/jsp/msg.jsp";
			}
			return "/jsp/product_list.jsp";
		}
	
	/**
	 * ��Ʒ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//1.��ȡpid
			String pid = request.getParameter("pid");
			
			//2.����service��ȡ������Ʒ ����:pid ����ֵ:product
			ProductService ps =new ProductServiceImpl();
			Product pro=ps.getById(pid);
			
			//3.��product����request���� ����ת���� /jsp/product_info.jsp
			request.setAttribute("bean", pro);
		} catch (Exception e) {
			request.setAttribute("msg", "");
			return "/jsp/msg.jsp";
		}
		
		/*String pid = request.getParameter("pid");
		System.out.println(pid);
		ProductService ps =new ProductServiceImpl();
		Product pro=ps.getById(pid);
		request.setAttribute("pro", pro);*/
		return "/jsp/product_info.jsp";
	}
}
