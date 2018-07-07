package com.store.model;

public class Product {
	
	private int productId;
	private double productPrice;
	private String productName;
	private int productInventory;
	private String productSeller;
	private String productDes;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId=productId;
	}
	public void setProductName(String productName) {
		this.productName=productName;
	}
    public String getProductName() {
    	return productName;
    }
    public double getProductPrice() {
    	return  productPrice;
    }
    public void setProductPrice(double productPrice) {
    	this.productPrice=productPrice;
    }
    public int getProductInventory() {
    	return productInventory;
    }
    public void setProductInventory(int productInventory ) {
    	this.productInventory=productInventory;
    }
    public String getProductSeller() {
    	return productSeller;
    }
    public void setProductSeller(String productSeller) {
    	this.productSeller=productSeller;
    }
    
    public String getProductDes() {
    	return productDes;
    }
    public void setProductDes(String productDes) {
    	this.productDes=productDes;
    }
}
