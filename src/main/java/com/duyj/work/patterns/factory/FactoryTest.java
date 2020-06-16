package com.duyj.work.patterns.factory;

/**
 * Created by LG on 2017/3/15.
 */
public class FactoryTest{
    public static void main(String[] args){
        IProduct product = Factory.product(2);
        if (product != null) {
            product.myfunction();
        }
    }
}
