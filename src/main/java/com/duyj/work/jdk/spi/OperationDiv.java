package com.duyj.work.jdk.spi;

public class OperationDiv implements IOperation {

    @Override
    public int operation(int a, int b) {
        return a / b;
    }
}
