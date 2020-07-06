package com.duyj2.work.javassist;

/**
 * Created by LG on 2017/12/3.
 */
public class OldClass {

    public int add() {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println("Old result: " + sum);
        return sum;
    }

}
