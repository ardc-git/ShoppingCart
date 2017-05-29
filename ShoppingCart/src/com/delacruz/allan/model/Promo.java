package com.delacruz.allan.model;

import java.util.Date;

public enum Promo {
	BUNDLE_3_FOR_2, 
	DISCOUNT_IF_MORETHAN_3,
	FREEBIES,
	WITH_PROMO_CODE;
	
	private Date expirationOfPromo;
	
	private float discounterPricePerUnit;
	
	private Item freeby;
	
	private String promoCode;
	
	private float discount;

	public Date getExpirationOfPromo() {
		return expirationOfPromo;
	}

	public void setExpirationOfPromo(Date expirationOfPromo) {
		this.expirationOfPromo = expirationOfPromo;
	}

	public float getDiscounterPricePerUnit() {
		return discounterPricePerUnit;
	}

	public void setDiscounterPricePerUnit(float discounterPricePerUnit) {
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

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	

}
