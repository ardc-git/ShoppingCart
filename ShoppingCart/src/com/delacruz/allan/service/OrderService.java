package com.delacruz.allan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.delacruz.allan.model.Item;
import com.delacruz.allan.model.Order;
import com.delacruz.allan.model.Promo;

public class OrderService {
	
	@SuppressWarnings("deprecation")
	private Date promoExpDate = new Date(2017,6,24);
	
	public Promo getPromo(Item item) {
		switch(item.getProductCode()) {
			case "ult_small": return Promo.BUNDLE_3_FOR_2;
			case "ult_large": return Promo.DISCOUNT_IF_MORETHAN_3;
			case "ult_medium": return Promo.FREEBIES;
		}
		return null;
	}
	
	public Promo getPromoWithCode(String promoCode) {
		if (promoCode.equals("I<3AMAYSIM")) {
			return Promo.WITH_PROMO_CODE;
		} else {
			return null;
		}
	}
	
	
	public void processOrder(Order order){
		Set<Promo> promos = order.getPromos();
		order.setOriginalTotalPrice(order.getItem().getPrice() * order.getQuantity());
		order.setDiscount(0);
		for (Promo promo: promos) {
			if(promo == Promo.BUNDLE_3_FOR_2) {
				int itemToBeDiscounted = Math.abs(order.getQuantity()/3);
				float discount = itemToBeDiscounted * order.getItem().getPrice();
				order.setDiscount(discount);
			} else if (promo == Promo.DISCOUNT_IF_MORETHAN_3 && order.getQuantity() > 3 && !isPromoExpired(Promo.DISCOUNT_IF_MORETHAN_3)) {
				float discountPerItem = order.getItem().getPrice() - 39.9f;
				order.setDiscount(discountPerItem * order.getQuantity());
			} else if (promo == Promo.FREEBIES) {
				List<Item> freebies = new ArrayList<Item>();
				for (int i = 1; i <= order.getQuantity(); i++){
					freebies.add(Item.ULT_1GB);
				}
				order.setFreebies(freebies);
			}
			if(promo.equals(Promo.WITH_PROMO_CODE)) {
				//System.out.println("With Legit Promo Code: " + promo);
				order.setDiscount(order.getDiscount() + ((order.getOriginalTotalPrice() - order.getDiscount()) * .1f));
			} else {
				//System.out.println("Not Legit Promo Code: " + promo);
			}
		}
		
	}
	
	private boolean isPromoExpired(Promo promo) {
		if (new Date().compareTo(promoExpDate) < 0) {
			return false;
		} else  {
			return true;
		}
	}
	
	
	
	public void setPromoExpDate(Date newExpDate) {
		promoExpDate = newExpDate;
	}

}
