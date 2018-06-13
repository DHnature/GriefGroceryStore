package com.store.dao;

import com.store.daoimpl.ProductDaoImpl;
import com.store.daoimpl.UserDaoImpl;

public class DaoFactory {
	public static DaoFactory instance=null;
	private DaoFactory() {
		
	}
	
	public synchronized static DaoFactory getInstance() {
		if(instance==null) {
			instance=new DaoFactory();
		}			
		return instance;
	}
	
	public  ProductDao creatProductDao() {
		ProductDao productDao=null;
	    try {
			productDao=new ProductDaoImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productDao;
		
	}
	
	public UserDao creatUserDao() {
		UserDao userDao=null;
		try {
			userDao=new UserDaoImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDao;
		
	}
	
	
	
	
	
	
	
	
	

}
