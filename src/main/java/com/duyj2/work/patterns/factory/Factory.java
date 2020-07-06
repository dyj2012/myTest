package com.duyj2.work.patterns.factory;

public class Factory{

    public static IProduct product(int k){
        if (k == 1) {
            return new Product1();
        } else if (k == 2) {
            return new Product2();
        }

        return null;
    }

}
