package com.duyj.work.patterns.chainofresponsibility;

public class Handler1 implements Handler {
    @Override
    public void handle() {
        System.out.println("handle1 invoke");
    }
}
