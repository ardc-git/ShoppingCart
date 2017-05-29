package com.delacruz.allan.model;

public enum Item {
	ULT_SMALL_1GB("ult_small","Unlimited 1GB",24.9f),
	ULT_MEDIUM_2GB("ult_medium","Unlimited 2GB",29.9f),
	ULT_LARGE_5GB("ult_large","Unlimited 5GB",44.9f),
	ULT_1GB("1gb","1 GB Data-pack",9.9f);
	
	private Item(String productCode, String productDescription, float price) {
		this.productCode = productCode;
		this.productDescription = productDescription;
		this.price = price;
	}
	private String productCode;
	private String productDescription;
	private float price;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
