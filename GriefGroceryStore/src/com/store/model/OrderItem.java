package com.store.model;

public class OrderItem {
	 private int orderId;
	    private Product product;
	    private int num;
	    public int getOrderId() {
	        return orderId;
	    }
	    public void setId(int orderId) {
	        this.orderId = orderId;
	    }
	    public Product getProduct() {
	        return product;
	    }
	    public void setProduct(Product product) {
	        this.product = product;
	    }
	    public int getNum() {
	        return num;
	    }
	    public void setNum(int num) {
	        this.num = num;
	    }
}
