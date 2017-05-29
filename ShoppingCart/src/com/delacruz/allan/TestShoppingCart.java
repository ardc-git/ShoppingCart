package com.delacruz.allan;

import com.delacruz.allan.cart.ShoppingCart;
import com.delacruz.allan.model.Item;

public class TestShoppingCart {

	public static void main(String[] args) {

		ShoppingCart shoppingCart = new ShoppingCart();
		String legitPromoCode = "I<3AMAYSIM";
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		
		shoppingCart.add(Item.ULT_MEDIUM_2GB,legitPromoCode);
		
		/*for (Item item: shoppingCart.getItems()) {
			System.out.println("Item: " + item);
		}*/
		System.out.println("Items and Freebies: " + shoppingCart.getItems());
		System.out.println("Freebies: " + shoppingCart.getFreebies());
		System.out.println("Total: " + shoppingCart.getTotal());
		
		
		
		
	}

}
