package com.itheima.service;


import com.itheima.domain.User;


public interface UserService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void regist(User user) throws Exception;
	
	User active(String code) throws Exception;
	
	public User login(String username, String password)throws Exception;

	

	}


