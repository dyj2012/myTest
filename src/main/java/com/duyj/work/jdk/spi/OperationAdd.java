package com.duyj.work.jdk.spi;

public class OperationAdd implements IOperation {
    @Override
    public int operation(int a, int b) {
        return a + b;
    }
}
