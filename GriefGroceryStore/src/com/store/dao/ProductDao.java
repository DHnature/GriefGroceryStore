package com.store.dao;

import java.util.List;

import com.store.model.Product;
import com.store.model.OrderItem;

public interface ProductDao {
	
	public List<Product> getCartProductList(String username);
	public List<Product> getAllProductList();
	
	
	

}
