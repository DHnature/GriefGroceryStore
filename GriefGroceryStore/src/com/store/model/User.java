package com.store.model;

public class User {
	    private int userId;
	    private String userName;
	    private String userPassword;
	    private double account;
	    private String validationProblem;
	    public int getUserId() {
	        return userId;
	    }
	    public void setUserId(int userId) {
	        this.userId = userId;
	    }
	    public String getUserName() {
	        return userName;
	    }
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	    public String getUserPassword() {
	        return userPassword;
	    }
	    public void setUserPassword(String userPassword) {
	        this.userPassword = userPassword;
	    }
	    public double getAccount() {
	    	return account;
	    }
	    public void setAccount(double account) {
	    	this.account=account;
	    }
	    public void setValidationProblem(String validationProblem) {
	    	this.validationProblem=validationProblem;
	    }
	    public String getValidationProblem() {
	    	return validationProblem;
	    }
	    
	    
}
