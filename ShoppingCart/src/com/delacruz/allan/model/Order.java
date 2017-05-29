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
	private float originalTotalPrice = 0;
	private float discount = 0;
	private List<Item> freebies = new ArrayList<Item>();
	
	private OrderService orderService;
	
	public Order() {
		super();
		orderService = new OrderService();
	}
	public float getOriginalTotalPrice() {
		return originalTotalPrice;
	}
	public void setOriginalTotalPrice(float originalTotalPrice) {
		this.originalTotalPrice = originalTotalPrice;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
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
		Promo promo = orderService.getPromoWithCode(promoCode);
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
		addPromo(orderService.getPromo(item));
	}
//	private void processOrder(){
//		OrderService.processOrder(this);
//	}

}
