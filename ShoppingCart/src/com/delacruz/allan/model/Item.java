package com.delacruz.allan.model;

public enum Item {
	ULT_SMALL("ult_small","Unlimited 1GB",24.9),
	ULT_MEDIUM("ult_medium","Unlimited 2GB",29.9),
	ULT_LARGE("ult_large","Unlimited 5GB",44.9),
	ULT_1GB("1gb","1 GB Data-pack",9.9);
	
	private Item(String productCode, String productDescription, double price) {
		this.productCode = productCode;
		this.productDescription = productDescription;
		this.price = price;
	}
	private String productCode;
	private String productDescription;
	private double price;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
