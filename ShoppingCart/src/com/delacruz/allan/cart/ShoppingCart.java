package com.delacruz.allan.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delacruz.allan.model.Item;
import com.delacruz.allan.model.Order;
import com.delacruz.allan.model.Promo;
import com.delacruz.allan.service.OrderService;

public class ShoppingCart {
	private Map<Item, Order> itemsMap;
	
	private boolean hasPromoCodeFlag = false;
	
	private OrderService orderService; 
			
	public ShoppingCart() {
		super();
		itemsMap = new HashMap<Item, Order>();
		orderService = new OrderService();
	}

	public boolean isHasPromoCodeFlag() {
		return hasPromoCodeFlag;
	}

	public void setHasPromoCodeFlag(boolean hasPromoCodeFlag) {
		this.hasPromoCodeFlag = hasPromoCodeFlag;
	}

	public void add(Item item) {
		add(item, null);
	}
	
	public void add(Item item, String promoCode) {
		Order order = new Order();
		
		if (itemsMap.get(item) == null) {
			//System.out.println("Not yet in map");
			order.setItem(item);
			itemsMap.put(item, order);
		} else {
			//System.out.println("Already in the map");
			order = itemsMap.get(item);
		}
		if (promoCode != null) {
			//System.out.println("Adding promoCode: " + promoCode);
			if (!isHasPromoCodeFlag() && Promo.WITH_PROMO_CODE.equals(orderService.getPromoWithCode(promoCode))) {
				setHasPromoCodeFlag(true);
			}
			
		}
		order.setQuantity(order.getQuantity() + 1);
		
	}
	
	public List<Item> getItems() {
		processCart();
		List<Item> items = new ArrayList<Item>();
		for (Item item: this.itemsMap.keySet()) {
			Order order = this.itemsMap.get(item);
			for (int i = 1; i <= (order.getQuantity()); i++) {
				items.add(item);
				//System.out.println("Item: " + item);
			}
			//List freebies as well
			List<Item> freebies = order.getFreebies();
			for (Item freeby: freebies) {
				items.add(freeby);
				//System.out.println("Freeby: " + freeby);
			}
		}
		return items;
	}
	
	public List<Item> getFreebies() {
		processCart();
		List<Item> items = new ArrayList<Item>();
		for (Item item: this.itemsMap.keySet()) {
			Order order = this.itemsMap.get(item);
			//List freebies as well
			List<Item> freebies = order.getFreebies();
			for (Item freeby: freebies) {
				items.add(freeby);
				//System.out.println("Freeby: " + freeby);
			}
		}
		return items;
	}
	
	public float getTotal() {
		processCart();
		float totalAmount = 0;
		for (Item item: this.itemsMap.keySet()) {
			Order order = this.itemsMap.get(item);
			totalAmount = totalAmount + (order.getOriginalTotalPrice() - order.getDiscount());
		}
		return totalAmount;
	}
	
	private void processCart() {
		// check if promocode was used
		for (Item item: this.itemsMap.keySet()) {
			Order order = this.itemsMap.get(item);
			if (isHasPromoCodeFlag()) {
				order.addPromo(Promo.WITH_PROMO_CODE);
			}
			orderService.processOrder(order);
		}
	}

}
