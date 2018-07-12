package com.store.service;

import java.util.ArrayList;

import com.store.dao.DaoFactory;
import com.store.dao.OrderDao;
import com.store.dao.ProductDao;
import com.store.json.JsonContext;
import com.store.model.Product;

import net.sf.json.JSONObject;

public class OrderService {
	private DaoFactory factory=DaoFactory.getInstance();
	private OrderDao orderDao =factory.creatOrderDao();
	
	public JSONObject paySingle(String username,Product product) {
        String payResult=orderDao.paySingle(username, product);
        JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        if(payResult.contains("success")) {
        	json=jsonContext.getSuccessObject(payResult, null, null);
        }
        else {
            json=jsonContext.getFailedObject(payResult);
        }
		return json;
	}
	
	
	public JSONObject payCart(String username,ArrayList<Product> productList) {
		String payResult=orderDao.payCart(username, productList);
        JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        if(payResult.contains("success")) {
        	json=jsonContext.getSuccessObject(payResult, null, null);
        }
        else {
            json=jsonContext.getFailedObject(payResult);
        }
		return json;
	}
	
	public JSONObject addOrder(String username,Product product) {
		String result=orderDao.addOrder(username, product);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        if(result.contains("success")) {
        	json=jsonContext.getSuccessObject(result, null, null);
        }
        else {
            json=jsonContext.getFailedObject(result);
        }
		return json;
		
	}
	
	public JSONObject deleteOrder(String username,Product product) {
		String result=orderDao.deleteOrder(username, product);
		JsonContext jsonContext=new JsonContext();
        JSONObject json=new JSONObject();
        if(result.contains("success")) {
        	json=jsonContext.getSuccessObject(result, null, null);
        }
        else {
            json=jsonContext.getFailedObject(result);
        }
		return json;
		
	}
	
	public JSONObject getCartProductList(String username) {		
		ArrayList<Product> list=(ArrayList<Product>) orderDao.getCartProductList(username);
		JSONObject json = new JSONObject();
		json.put("productlist", list);
		JSONObject json1=new JSONObject();
		JsonContext jsonContext=new JsonContext();
		json1=jsonContext.getSuccessObject("加载购物车页面成功！！！",json,null);
		return json1;
	}


	public JSONObject getOrder(String username) {
		// TODO Auto-generated method stub
		ArrayList<Product> list=(ArrayList<Product>) orderDao.getOrderList(username);
		JSONObject json = new JSONObject();
		json.put("productlist", list);
		JSONObject json1=new JSONObject();
		JsonContext jsonContext=new JsonContext();
		json1=jsonContext.getSuccessObject("加载订单成功！！！",json,null);
		return json1;
	}
	
}
