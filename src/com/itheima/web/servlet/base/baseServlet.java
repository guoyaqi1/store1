package com.itheima.web.servlet.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用的Servlet
 */

public class baseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * baseservlet的抽取:
	         统一处理请求的方法
		request.getParameter("method")
		通过反射获取方法对象
		让方法执行
	统一处理请求转发
		规定所有的方法都有返回值:string
		若需要转发,返回转发路径
		若不需要转发,返回null
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1获取的方法名称
			String mName = request.getParameter("method");
			
			//1.1判断参数是否为空，若为空，执行默认的方法
			if(mName == null || mName.trim().length()==0){
				mName = "index";
			}
			
			//2获取方法对象
			Method method = this.getClass().getMethod(mName, HttpServletRequest.class,HttpServletResponse.class);
			
			//3让方法执行,接收返回值
			String path=(String) method.invoke(this, request,response);
			
			//4判断返回值是否为空，若不为空则统一处理请求转发
			if(path != null){
				request.getRequestDispatcher(path).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	public String index(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println("亲，不要捣乱");
		return null;
	}	
}
	  
	  


