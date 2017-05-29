/**
 * 
 */
package com.delacruz.allan.service;

import static org.junit.Assert.*;

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
	
	private OrderService orderService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		orderService = new OrderService();
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
		promo = orderService.getPromo(Item.ULT_SMALL_1GB);
		assertEquals(promo, Promo.BUNDLE_3_FOR_2);
		promo = orderService.getPromo(Item.ULT_MEDIUM_2GB);
		assertEquals(promo, Promo.FREEBIES);
		promo = orderService.getPromo(Item.ULT_LARGE_5GB);
		assertEquals(promo, Promo.DISCOUNT_IF_MORETHAN_3);
	}
	
	@Test
	public void getPromoWithCodeTest() {
		Promo legitPromo = orderService.getPromoWithCode("I<3AMAYSIM");
		assertNotNull(legitPromo);
		
		Promo unknownPromo = orderService.getPromoWithCode("SAMPLE");
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
		assertTrue(shoppingCart.getTotal() == ((Item.ULT_1GB.getPrice() * 4) - ((Item.ULT_1GB.getPrice() * 4f)*.1f)));
	}
	
	@Test
	public void processOrderTestForSmall() {
		//only 2 units
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		
		assertEquals(shoppingCart.getItems().size(), 2);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(shoppingCart.getTotal() == (Item.ULT_SMALL_1GB.getPrice() * 2));
		
		//more than 3
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		
		assertEquals(shoppingCart.getItems().size(), 7);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(String.format("%10.2f", shoppingCart.getTotal()).equals(String.format("%10.2f", (Item.ULT_SMALL_1GB.getPrice() * 5))));
		
		//more than 3 with promo code
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB, legitPromoCode);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		
		assertEquals(shoppingCart.getItems().size(), 7);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertTrue(String.format("%10.2f", shoppingCart.getTotal()).equals(String.format("%10.2f",((Item.ULT_SMALL_1GB.getPrice() * 5) - ((Item.ULT_SMALL_1GB.getPrice() * 5)*.1)))));
	}
	
	@Test
	public void processOrderTestForMedium() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		
		
		assertEquals(shoppingCart.getItems().size(), 14);
		assertEquals(shoppingCart.getFreebies().size(), 7);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f", (Item.ULT_MEDIUM_2GB.getPrice() * 7)));
		
		//with promo code
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB, legitPromoCode);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		
		
		assertEquals(shoppingCart.getItems().size(), 14);
		assertEquals(shoppingCart.getFreebies().size(), 7);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f",((Item.ULT_MEDIUM_2GB.getPrice() * 7) - ((Item.ULT_MEDIUM_2GB.getPrice() * 7)*.1))));
	}
	
	@Test
	public void processOrderTestForLarge() {
		//less than 3
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		
		
		assertEquals(shoppingCart.getItems().size(), 2);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f", (Item.ULT_LARGE_5GB.getPrice() * 2)));
		
		//more than 3
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		
		assertEquals(shoppingCart.getItems().size(), 4);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f", (39.9d * 4)));

		//more than 3 with promo code
		shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB,legitPromoCode);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		
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
		
		float ult1GBPrice = Item.ULT_1GB.getPrice() * 4;
		
		//with promo code
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB,legitPromoCode);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		float ultSmallPrice = Item.ULT_SMALL_1GB.getPrice() * (4 - 1);
		
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		
		float ultMediumPrice = Item.ULT_MEDIUM_2GB.getPrice() * 5;
		
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		
		float ultLargePrice = 39.9f * 5;
		
		float totalPrice = ult1GBPrice + ultSmallPrice + ultMediumPrice + ultLargePrice;
		System.out.println("Unit Test total Price for mix: " + totalPrice);
		assertEquals(shoppingCart.getItems().size(), 23);
		assertEquals(shoppingCart.getFreebies().size(), 5);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()),String.format("%10.2f",(totalPrice - (totalPrice*.1))));
	}
	
	@Test
	public void testScenario1() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		shoppingCart.add(Item.ULT_LARGE_5GB);
				
		assertEquals(shoppingCart.getItems().size(), 4);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()).trim(),"94.70");
	}
	
	@Test
	public void testScenario2() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL_1GB);
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		shoppingCart.add(Item.ULT_LARGE_5GB);
		
		assertEquals(shoppingCart.getItems().size(), 6);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()).trim(),"209.40");
	}
	
	@Test
	public void testScenario3() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_SMALL_1GB);
		
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		shoppingCart.add(Item.ULT_MEDIUM_2GB);
		
		assertEquals(shoppingCart.getItems().size(), 5);
		assertEquals(shoppingCart.getFreebies().size(), 2);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()).trim(),"84.70");
	}
	
	@Test
	public void testScenario4() {
		//without promo code
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.add(Item.ULT_1GB);
		
		shoppingCart.add(Item.ULT_SMALL_1GB,legitPromoCode);
		
		assertEquals(shoppingCart.getItems().size(), 2);
		assertEquals(shoppingCart.getFreebies().size(), 0);
		assertEquals(String.format("%10.2f", shoppingCart.getTotal()).trim(),"31.32");
	}
	
	

}
