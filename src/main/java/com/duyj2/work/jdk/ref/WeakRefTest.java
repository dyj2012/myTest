package com.duyj2.work.jdk.ref;

import java.lang.ref.WeakReference;

//弱引用：gc时对象被回收
public class WeakRefTest {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        WeakReference<Object> weak = new WeakReference<Object>(object);

        System.out.println(weak.get());

        object = null;
        System.gc();
        Thread.sleep(1000);

        System.out.println(weak.get());
    }
}
