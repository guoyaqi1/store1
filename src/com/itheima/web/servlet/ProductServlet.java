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
 *商品详情展示
 */

public class ProductServlet extends baseServlet {
	private static final long serialVersionUID = 1L;
	

		/**
		 * 分页商品展示
		 */
		public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			try {
				//1.获取pagenumber cid  设置pagesize
				/*String parameter = request.getParameter("pageNumber");*/
				int pageNumber = 1;
				
				try {
					pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				} catch (NumberFormatException e) {
				}
				
				int pageSize = 12;
				String cid = request.getParameter("cid");
				
				//2.调用service 分页查询商品参数:3个, 返回值:pagebean
				ProductService ps = new ProductServiceImpl();
				PageBean<Product> bean=ps.findByPage(pageNumber,pageSize,cid);
				
				//3.将pagebean放入request中,请求转发 product_list.jsp
				request.setAttribute("pb", bean);
			} catch (Exception e) {
				request.setAttribute("msg", "分页查询失败");
				return "/jsp/msg.jsp";
			}
			return "/jsp/product_list.jsp";
		}
	
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//1.获取pid
			String pid = request.getParameter("pid");
			
			//2.调用service获取单个商品 参数:pid 返回值:product
			ProductService ps =new ProductServiceImpl();
			Product pro=ps.getById(pid);
			
			//3.将product放入request域中 请求转发到 /jsp/product_info.jsp
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
