package com.delacruz.allan.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.delacruz.allan.service.OrderService;

public class Order {
	private Item item;
	private int quantity;
	private Set<Promo> promos = new HashSet<Promo>();
	private double originalTotalPrice = 0;
	private double discount = 0;
	private List<Item> freebies = new ArrayList<Item>();
	
	public double getOriginalTotalPrice() {
		return originalTotalPrice;
	}
	public void setOriginalTotalPrice(double originalTotalPrice) {
		this.originalTotalPrice = originalTotalPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public List<Item> getFreebies() {
		return freebies;
	}
	public void setFreebies(List<Item> freebies) {
		this.freebies = freebies;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
		setPromo(item);
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		//this.processOrder();
	}
	public Set<Promo> getPromos() {
		return promos;
	}
	public void setPromos(Set<Promo> promos) {
		this.promos = promos;
	}
	
	public void addPromoCode(String promoCode) {
		Promo promo = OrderService.getPromoWithCode(promoCode);
		if (promo != null) {
			addPromo(promo);
		}
	}
	public void addPromo(Promo promo) {
		if (promo != null) {
			this.promos.add(promo);
		}
	}
	
	private void setPromo(Item item) {
		addPromo(OrderService.getPromo(item));
	}
//	private void processOrder(){
//		OrderService.processOrder(this);
//	}

}
