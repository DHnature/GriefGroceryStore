package com.store.service;

import java.util.ArrayList;
import java.util.List;

import com.store.dao.DaoFactory;
import com.store.dao.ProductDao;
import com.store.json.JsonContext;
import com.store.model.Product;

import net.sf.json.JSONObject;

public class ProductService {
	private DaoFactory factory=DaoFactory.getInstance();
	private ProductDao productDao =factory.creatProductDao();
	
	public JSONObject getAllProducts() {
		ArrayList<Product> list=(ArrayList<Product>) productDao.getAllProductList();
		JSONObject json = new JSONObject();
		JSONObject json1=new JSONObject();
		JsonContext jsonContext=new JsonContext();
		json.put("allProducts",list);
		json1=jsonContext.getSuccessObject("主页加载成功！！！", json, null);
		System.out.println(json1);
		return json1;
		
	}


}
