package com.store.service;


import java.util.ArrayList;
import java.util.Map;

import com.store.dao.DaoFactory;
import com.store.dao.UserDao;
import com.store.json.JsonContext;
import com.store.model.Product;
import com.store.model.User;
import com.store.util.DaoUtil;
import net.sf.json.JSONObject;

public class UserService {
	DaoFactory factory=DaoFactory.getInstance();
	UserDao userDao=factory.creatUserDao();
	
	
	
	
	
	public JSONObject repassword(String username,String newPassword){
		String result=userDao.repassword(username, newPassword);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        if(result.contains("success")) {
        	json=jsonContext.getSuccessObject("1", null, null);
        }
        else {
            json=jsonContext.getFailedObject(result);
        }
		return json;
		
		
	}
	
	public JSONObject getValidationProblem(String username){
		String question=userDao.getValidationProblem(username);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        if(question.contains("success")) {
        	json=jsonContext.getSuccessObject(question, null, null);
        }
        else {
            json=jsonContext.getFailedObject(question);
        }
		return json;
		
		
	}
	
	public JSONObject usernameComfire(String username) {
		JsonContext jsonContext=new JsonContext();
		JSONObject json=new JSONObject();	
		JSONObject json2=new JSONObject();
		User user=DaoUtil.queryUserByUsername(username);
    	if(user.getUserName()!=null) {
    		String question=userDao.getValidationProblem(username);
    		json2.put("question", question);
    		json2.put("username", user.getUserName());
    		json=jsonContext.getSuccessObject("1", json2, null);
    		
    	}
    	else {
    		json=jsonContext.getFailedObject("用户名不存在！！！");
    	}
		return json;
	}
	

	public JSONObject validationProblemComfire(String username,String answer){
		String result=userDao.validationProblemComfire(username,answer);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        JSONObject json2=new JSONObject();
        if(result.contains("success")) {
        	json2.put("username", username);
        	json=jsonContext.getSuccessObject("1", json2, null);
        }
        else {
            json=jsonContext.getFailedObject(result);
        }
		return json;
				
	}


	public JSONObject regisiter(User user) {
		String result=userDao.regisiter(user);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        if(result.contains("success")) {
        	json=jsonContext.getSuccessObject("1", null, null);
        }
        else {
            json=jsonContext.getFailedObject(result);
        }
		return json;
				
	}
	
	public JSONObject getProfilePhoto(String username) {
		String result=userDao.getProfilePhoto(username);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        JSONObject json1=new JSONObject();
        if(result.contains("success")) {
        	json1.put("imgurl", result);
        	json=jsonContext.getSuccessObject("1", json1, null);
        }
        else {
            json=jsonContext.getFailedObject(result);
        }
		return json;

		
	}


	public JSONObject getUserInfo(String username) {
		Map<String,String> result=userDao.getUserInfo(username);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        JSONObject json1=new JSONObject();
        if(result!=null) {
        	json1.put("userInfo", result);
        	json=jsonContext.getSuccessObject("1", json1, null);
        }
        else {
            json=jsonContext.getFailedObject("0");
        }
		return json;
	}

	public JSONObject login(String username, String password) {
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
