package com.delacruz.allan;

import java.text.NumberFormat;

import com.delacruz.allan.cart.ShoppingCart;
import com.delacruz.allan.model.Item;

public class TestShoppingCart {

	public static void main(String[] args) {

		ShoppingCart shoppingCart = new ShoppingCart();
		String legitPromoCode = "I<3AMAYSIM";
//		shoppingCart.add(Item.ULT_1GB);
//		shoppingCart.add(Item.ULT_1GB);
//		shoppingCart.add(Item.ULT_1GB);
		
//		shoppingCart.add(Item.ULT_LARGE);
//		shoppingCart.add(Item.ULT_LARGE);
//		shoppingCart.add(Item.ULT_LARGE);
//		shoppingCart.add(Item.ULT_LARGE);
		
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		
		shoppingCart.add(Item.ULT_MEDIUM,legitPromoCode);
		
		/*for (Item item: shoppingCart.getItems()) {
			System.out.println("Item: " + item);
		}*/
		shoppingCart.getItems();
		System.out.println("Total: " + shoppingCart.getTotal());
		
		
		
		
	}

}
