/**
 * 
 */
package com.delacruz.allan.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.delacruz.allan.model.Item;
import com.delacruz.allan.model.Promo;

/**
 * @author ALLAN
 *
 */
public class OrderServiceTest {
	
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
	
		
	

}
