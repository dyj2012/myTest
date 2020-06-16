package com.duyj.work.patterns.factorymethod;

public class Factory2 implements IFactory{
    public IProduct product(){
        return new Product2();
    }
}
