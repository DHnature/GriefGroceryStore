package com.store.service;

import com.store.dao.DaoFactory;
import com.store.dao.UserDao;
import com.store.json.JsonContext;
import com.store.model.User;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class LoginService {
	
	public JSONObject login(String username,String password) {
		DaoFactory factory=DaoFactory.getInstance();
		UserDao userDao=factory.creatUserDao();
		JsonContext jsonContext=new JsonContext();
		JSONObject jsonUser=new JSONObject();
		JSONObject json=new JSONObject();
		User user;
		boolean isSuccess=false;
		user=userDao.login(username, password);
		
		if(user.getUserName()!=null) {
			System.out.println("username为"+username +"   password为"+password+"    存在！！！");
			jsonUser.put("user", user);
			System.out.println(jsonUser.get("user"));
			json=jsonContext.getSuccessObject("登录成功！！！", jsonUser, null);
		}
		else {
			System.out.println("username为"+username +"   password为"+password+"    不存在！！！");
			 json=jsonContext.getFailedObject("登录失败！！！");
			
		}
	
		return json;
	}
	
	
	
	
	

}
