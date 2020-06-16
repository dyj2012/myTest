package com.duyj.work.concurrent.locks;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) {

        Thread t = new Thread() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("t end");
            }
        };

        t.start();
        LockSupport.unpark(t);
        System.out.println("main end");
    }

}
