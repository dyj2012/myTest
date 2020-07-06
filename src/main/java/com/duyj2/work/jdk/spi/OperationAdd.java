package com.duyj2.work.jdk.spi;

public class OperationAdd implements IOperation {
    @Override
    public int operation(int a, int b) {
        return a + b;
    }
}
