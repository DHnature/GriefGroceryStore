package com.store.dao;

import java.util.List;

import com.store.model.Product;
import com.store.model.User;

public interface OrderDao {
	public String paySingle(String username,Product product);
	public String payCart(String username,List<Product> productList);	
    public String addOrder(String username,Product product);
    public String deleteOrder(String username,Product product); 
	public List<Product> getCartProductList(String username);
	public List<Product> getOrderList(String username);

}
