package com.duyj.uitl;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/02
 */
public class PrintTimeUtils {

    public static void time(CallAble callAble, String... name) {
        long s = System.currentTimeMillis();
        callAble.callBack();
        if (ArrayUtils.isNotEmpty(name)) {
            System.out.println(name[0] + (System.currentTimeMillis() - s) + "ms");
        } else {
            System.out.println((System.currentTimeMillis() - s) + "ms");
        }

    }
}
