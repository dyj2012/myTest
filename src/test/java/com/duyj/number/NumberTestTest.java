package com.duyj.number;

import com.duyj.uitl.DoWhileCountUtils;
import com.duyj.uitl.PrintTimeUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/15
 */
public class NumberTestTest {

    BigDecimal bd1 = BigDecimal.valueOf(6547.43);
    BigDecimal bd2 = BigDecimal.valueOf(12345.12);
    BigDecimal warn = BigDecimal.valueOf(0.9);
    BigDecimal forbid = BigDecimal.valueOf(1.2);
    int count = 1000000;

    @Test
    public void divide() {
        PrintTimeUtils.time(() -> DoWhileCountUtils.doWhile(count, () -> NumberTest.divide(bd1, bd2)));
    }

    @Test
    public void multiply() {
        PrintTimeUtils.time(() -> DoWhileCountUtils.doWhile(count, () -> {
            NumberTest.multiply(bd1, warn);
            NumberTest.multiply(bd1, forbid);
        }));
    }

    @Test
    public void formatTest() {
        String[] strs = new String[]{
                "1", "2"
        };
        Object[] strs2 = new Object[]{
                "1", "2"
        };

        System.out.println(String.format("111%s,222%s", strs));
        System.out.println(String.format("111%s,222%s", strs2));
    }
}