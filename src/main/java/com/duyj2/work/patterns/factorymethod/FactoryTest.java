package com.duyj2.work.patterns.factorymethod;

/**
 * Created by LG on 2017/3/15.
 */
public class FactoryTest{
    public static void main(String[] args){
        IFactory factory1 = new Factory1();
        IProduct product = factory1.product();
        product.myfunction();

        IFactory factory2 = new Factory2();
        product = factory2.product();
        product.myfunction();
    }
}
