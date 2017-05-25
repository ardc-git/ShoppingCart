package com.delacruz.allan.model;

import java.util.Date;

public enum Promo {
	BUNDLE_3_FOR_2, 
	DISCOUNT_IF_MORETHAN_3,
	FREEBIES,
	WITH_PROMO_CODE;
	
	private Date expirationOfPromo;
	
	private double discounterPricePerUnit;
	
	private Item freeby;
	
	private String promoCode;
	
	private double discount;

	public Date getExpirationOfPromo() {
		return expirationOfPromo;
	}

	public void setExpirationOfPromo(Date expirationOfPromo) {
		this.expirationOfPromo = expirationOfPromo;
	}

	public double getDiscounterPricePerUnit() {
		return discounterPricePerUnit;
	}

	public void setDiscounterPricePerUnit(double discounterPricePerUnit) {
		this.discounterPricePerUnit = discounterPricePerUnit;
	}

	public Item getFreeby() {
		return freeby;
	}

	public void setFreeby(Item freeby) {
		this.freeby = freeby;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	

}
