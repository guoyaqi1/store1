package com.itheima.web.servlet;

import java.io.IOException;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.constant.Constant;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.utils.UUIDUtils;




import com.itheima.web.servlet.base.baseServlet;

/**
 * 用户模块
 */

public class UserServlet extends baseServlet {
	private static final long serialVersionUID = 1L;
	


	/**
	 *退出
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//点击 index.jsp上 退出 连接,向userservlet发送请求
		//销毁session 重定向到index.jsp
		request.getSession().invalidate();
		
		response.sendRedirect(request.getContextPath());
		return null;
	} 
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取用户名和密码
			String username = request.getParameter("username");
			String password	= request.getParameter("password");
			
			//2.调用Service完成登录，返回值:user
			UserService us = new UserServiceImpl();
			User user=us.login(username,password);
			
			//3.判断user 根据结果生成提示
			if(user == null){
				//若user为空:提示 用户名和密码 跳转到login.jsp
				//用户名密码不匹配
				request.setAttribute("msg", "用户名密码不匹配");;
				return "/jsp/login.jsp";
			}		
			
			//登陆成功，保存用户登录状态
			request.getSession().setAttribute("user", user);
			
			
			////////////////////记住用户名/////////////////////
			//判断是否勾选记住用户名
			if(Constant.SAVE_NAME.equals(request.getParameter("savename"))){
				Cookie c = new Cookie("saveName", URLEncoder.encode(username, "utf-8"));
				
				c.setMaxAge(Integer.MAX_VALUE);
				c.setPath(request.getContextPath()+"/");
				
				response.addCookie(c);
			}
			///////////////////////////////////////
			
			//跳转到 index.jsp
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "用户登录失败");
			return "/jsp/msg.jsp";
		}
		
		return null;
	}
	
	/**
	 * 跳转到登录页面 login.jsp
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}  
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1.封装对象
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			//1.1手动封装uid
			user.setUid(UUIDUtils.getId());
			
			//1.2手动封装激活状态 state
			user.setState(Constant.USER_IS_NOT_ACTIVE);
			
			//1.3手动封装激活码code
			user.setCode(UUIDUtils.getCode());
			
			//2.调用service完成注册
			UserService us=new UserServiceImpl();
			us.regist(user);
			
			//3.页面转发 提示信息
			request.setAttribute("msg", "恭喜你注册成功!");
		}catch (Exception e) {
			e.printStackTrace();
			//转发到msg.jsp
			request.setAttribute("msg", "用户注册失败!");
			return "/jsp/msg.jsp";
		}
		
		return "/jsp/msg.jsp";
	}

	/**跳转到注册页面
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}
	
}
