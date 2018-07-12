package com.store.dao;

import java.util.List;
import java.util.Map;

import com.store.model.Product;
import com.store.model.User;

import net.sf.json.JSONObject;

public interface UserDao {
public User login(String username,String password);
public String repassword(String username,String newPassword);
public String getValidationProblem(String username);
public String validationProblemComfire(String username,String answer);
public String regisiter(User user);
public String getProfilePhoto(String username);
public Map<String,String> getUserInfo(String username);
}
