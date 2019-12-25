package com.itheima.service.impl;



import com.itheima.constant.Constant;
import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.MailUtils;



public class UserServiceImpl implements UserService {

	@Override
	public void regist(User user) throws Exception {

		
			//1.调用DAO完成注册
					UserDao ud=new UserDaoImpl();
					ud.save(user);//保存用户
					
					//2.发送激活邮件
					String emailMsg="恭喜"+user.getName()+":成为我们商城的一员,<a href='http://localhost/store/user?method=active&code="+user.getCode()+"'>鐐规婵�娲�</a>";
					MailUtils.sendMail(user.getEmail(), emailMsg);
				}

	@Override
	//用户登录
	public User login(String username, String password) throws Exception {
		
	    UserDao ud=new UserDaoImpl();
		
		return ud.getByUsernameAndPwd(username,password);
	}

	@Override
	public User active(String code) throws Exception {
		UserDao ud=new UserDaoImpl();
		//1.
		User user=ud.getByCode(code);
		
		//1.1 
		if(user == null){
			return null;
		}
		
		//2.
		user.setState(Constant.USER_IS_ACTIVE);
		user.setCode(null);
		
		ud.update(user);
		return user;
	}
	}
	


