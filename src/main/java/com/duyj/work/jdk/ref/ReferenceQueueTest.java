package com.duyj.work.jdk.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceQueueTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        testWeak();
        testSoft();
    }

    //弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象
    public static void testWeak() throws InterruptedException {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        Thread thread = new Thread(() -> {
            int cnt = 0;
            try {
                WeakReference<byte[]> k;
                while ((k = (WeakReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + " Weak回收了:" + k);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //thread.setDaemon(true);
        thread.start();

        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, referenceQueue);
            map.put(weakReference, value);
        }
        System.out.println("Weak map.size->" + map.size());
    }

    // 软引用关联着的对象，在内存不足的时候JVM才会回收,常做缓存
    public static void testSoft() throws InterruptedException {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        Thread thread = new Thread(() -> {
            int cnt = 0;
            try {
                SoftReference<byte[]> k;
                while ((k = (SoftReference) referenceQueue.remove()) != null) {
                    System.out.println((cnt++) + " Soft回收了:" + k);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //thread.setDaemon(true);
        thread.start();

        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            byte[] bytes = new byte[_1M];
            SoftReference<byte[]> softReference = new SoftReference<byte[]>(bytes, referenceQueue);
            map.put(softReference, value);
        }
        System.out.println("Soft map.size->" + map.size());
    }


}
