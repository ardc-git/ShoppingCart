/**
 * 
 */
package com.delacruz.allan.service;

import static org.junit.Assert.*;

import java.text.NumberFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.delacruz.allan.cart.ShoppingCart;
import com.delacruz.allan.model.Item;
import com.delacruz.allan.model.Promo;

/**
 * @author ALLAN
 *
 */
public class OrderServiceTest {
	
	private final String legitPromoCode = "I<3AMAYSIM";
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getPromoTest() {
		Promo promo = null;
		promo = OrderService.getPromo(Item.ULT_SMALL);
		assertEquals(promo, Promo.BUNDLE_3_FOR_2);
		promo = OrderService.getPromo(Item.ULT_MEDIUM);
		assertEquals(promo, Promo.FREEBIES);
		promo = OrderService.getPromo(Item.ULT_LARGE);
		assertEquals(promo, Promo.DISCOUNT_IF_MORETHAN_3);
	}
	
	@Test
	public void getPromoWithCodeTest() {
		Promo legitPromo = OrderService.getPromoWithCode("I<3AMAYSIM");
		assertNotNull(legitPromo);
		
		Promo unknownPromo = OrderService.getPromoWithCode("SAMPLE");
		assertNull(unknownPromo);
	}
	
	@Test
	public void processOrderTestFor1GB() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		
		assertEquals(shoppingCart.getItems().size(), 4);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(shoppingCart.getTotal() == (Item.ULT_1GB.getPrice() * 4));
		
		//with promo code
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB,legitPromoCode);
		shoppingCart.add(Item.ULT_1GB);
		
		assertEquals(shoppingCart.getItems().size(), 4);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(shoppingCart.getTotal() == ((Item.ULT_1GB.getPrice() * 4) - ((Item.ULT_1GB.getPrice() * 4)*.1)));
	}
	
	@Test
	public void processOrderTestForSmall() {
		//only 2 units
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		
		
		assertEquals(shoppingCart.getItems().size(), 2);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(shoppingCart.getTotal() == (Item.ULT_SMALL.getPrice() * 2));
		
		//more than 3
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		
		
		assertEquals(shoppingCart.getItems().size(), 7);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(String.format("%10.2f", shoppingCart.getTotal()).equals(String.format("%10.2f", (Item.ULT_SMALL.getPrice() * 5))));
		
		//more than 3 with promo code
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL, legitPromoCode);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		
		
		assertEquals(shoppingCart.getItems().size(), 7);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(String.format("%10.2f", shoppingCart.getTotal()).equals(String.format("%10.2f",((Item.ULT_SMALL.getPrice() * 5) - ((Item.ULT_SMALL.getPrice() * 5)*.1)))));
	}
	
	@Test
	public void processOrderTestForMedium() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		
		
		assertEquals(shoppingCart.getItems().size(), 14);
		assertEquals(shoppingCart.getFreebies().size(), 7);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f", (Item.ULT_MEDIUM.getPrice() * 7)));
		
		//with promo code
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM, legitPromoCode);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		
		
		assertEquals(shoppingCart.getItems().size(), 14);
		assertEquals(shoppingCart.getFreebies().size(), 7);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f",((Item.ULT_MEDIUM.getPrice() * 7) - ((Item.ULT_MEDIUM.getPrice() * 7)*.1))));
	}
	
	@Test
	public void processOrderTestForLarge() {
		//less than 3
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		
		
		assertEquals(shoppingCart.getItems().size(), 2);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f", (Item.ULT_LARGE.getPrice() * 2)));
		
		//more than 3
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		
		assertEquals(shoppingCart.getItems().size(), 4);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f", (39.9d * 4)));

		//more than 3 with promo code
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE,legitPromoCode);
		shoppingCart.add(Item.ULT_LARGE);
		
		assertEquals(shoppingCart.getItems().size(), 4);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f",((39.9d * 4) - ((39.9d * 4)*.1))));
	}
	
	@Test
	public void processOrderTestForMix() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		shoppingCart.add(Item.ULT_1GB);
		
		double ult1GBPrice = Item.ULT_1GB.getPrice() * 4;
		
		//with promo code
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL);
		shoppingCart.add(Item.ULT_SMALL,legitPromoCode);
		shoppingCart.add(Item.ULT_SMALL);
		
		double ultSmallPrice = Item.ULT_SMALL.getPrice() * (4 - 1);
		
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		shoppingCart.add(Item.ULT_MEDIUM);
		
		double ultMediumPrice = Item.ULT_MEDIUM.getPrice() * 5;
		
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		shoppingCart.add(Item.ULT_LARGE);
		
		double ultLargePrice = 39.9d * 5;
		
		double totalPrice = ult1GBPrice + ultSmallPrice + ultMediumPrice + ultLargePrice;
		System.out.println("Unit Test total Price for mix: " + totalPrice);
		assertEquals(shoppingCart.getItems().size(), 23);
		assertEquals(shoppingCart.getFreebies().size(), 5);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f",(totalPrice - (totalPrice*.1))));
	}
	
	

}
