package com.duyj2.work.spring.aop.proxyFactory;

public class Client {

    public String action1(String thing) {
        System.out.println("do a thing:" + thing);
        return thing;
    }

    public String action2(int a, int b) {
        return a + b + "";
    }

}
