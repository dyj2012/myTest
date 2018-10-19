package com.duyj.uitl;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/08/15
 */
public class DoWhileCountUtils {

    public static void doWhile(int count, CallAble call) {
        for (int i = 0; i < count; i++) {
            call.callBack();
        }
    }
}
