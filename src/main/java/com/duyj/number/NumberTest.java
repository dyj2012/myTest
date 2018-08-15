package com.duyj.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/15
 */
public class NumberTest {

    public static void divide(BigDecimal bd1, BigDecimal bd2) {
        bd1.divide(bd2, 6, RoundingMode.HALF_UP);
    }

    public static void multiply(BigDecimal bd1, BigDecimal bd2) {
        bd1.multiply(bd2);
    }

}
