package com.store.dao;

import com.store.model.Product;
import com.store.model.User;

public interface OrderDao {
	
     public void addOrderItem(String name,int uid,int pid,int number);

}
