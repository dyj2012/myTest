package com.duyj.uitl;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/02
 */
public class PrintTimeUtils {

    public static void time(CallAble callAble) {
        long s = System.currentTimeMillis();
        callAble.callBack();
        System.out.println(System.currentTimeMillis() - s + "ms");
    }
}
