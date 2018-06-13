package com.store.dao;

import java.util.List;

import com.store.model.Product;
import com.store.model.User;

import net.sf.json.JSONObject;

public interface UserDao {
public User login(String username,String password);
public String paySingle(String username,Product product);
public String payCart(String username,List<Product> productList);
public String addOrder(String username,Product product);
public String deleteOrder(String username,Product product);

}
