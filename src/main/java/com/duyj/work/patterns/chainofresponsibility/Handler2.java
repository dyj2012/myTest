package com.duyj.work.patterns.chainofresponsibility;

public class Handler2 implements Handler {
    @Override
    public void handle() {
        System.out.println("handle2 invoke");
    }
}
