package com.duyj2.work.patterns.factorymethod;

public class Factory1 implements IFactory{
    public IProduct product(){
        return new Product1();
    }
}
