package com.delacruz.allan.util;

import java.math.BigDecimal;

public class ShoppingCartUtil {
	
	public static float roundOfTwoDecimalPlaces(float floatToRound) {
		BigDecimal bd = new BigDecimal(Float.toString(floatToRound));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
	}

}
