package com.duyj.work.concurrent;

import com.duyj.work.Person;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;

//原子类
public class AtomicTest {

    //AtomicLong是作用是对长整形进行原子操作
    static void testAtomicInteger() throws InterruptedException {
        int threadcount = 100;
        AtomicInteger a = new AtomicInteger(0);
        //AtomicLong al = new AtomicLong(0);
        //AtomicBoolean b = new AtomicBoolean(false);

        final CountDownLatch l = new CountDownLatch(threadcount);

        for (int i = 0; i < threadcount; ++i) {
            final int index = i;
            new Thread(() -> {
                for (int j = 0; j < 100; ++j) {
                    a.getAndIncrement();
                }
                //System.out.println("finished : " + index);
                l.countDown();
            }).start();
        }

        l.await();
        System.out.println("SafeSeq with atomic: " + a.get());
    }

    //AtomicLongArray的作用则是对"长整形数组"进行原子操作。
    static void testAtomicLongArray() {
        // 新建AtomicLongArray对象
        long[] arrLong = new long[]{10, 20, 30, 40, 50};
        AtomicLongArray ala = new AtomicLongArray(arrLong);
        //AtomicIntegerArray c = new AtomicIntegerArray(10);

        ala.set(0, 100);
        for (int i = 0, len = ala.length(); i < len; i++)
            System.out.println(ala.get(i));

        System.out.println("getAndDecrement(0)" + ala.getAndDecrement(0));
        System.out.println("decrementAndGet(1)" + ala.decrementAndGet(1));
        System.out.println("getAndIncrement(2)" + ala.getAndIncrement(2));
        System.out.println("incrementAndGet(3)" + ala.incrementAndGet(3));

        System.out.println("addAndGet(100)" + ala.addAndGet(0, 100));
        System.out.println("getAndAdd(100)" + ala.getAndAdd(1, 100));

        System.out.println("compareAndSet()" + ala.compareAndSet(2, 31, 1000));
        System.out.println("get(2)" + ala.get(2));
    }

    //AtomicReference是作用是对"对象"进行原子操作。
    static void testAtomicReference() {
        // 创建两个Person对象
        Person p1 = new Person(1);
        Person p2 = new Person(2);
        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference ar = new AtomicReference(p1);
        // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
        ar.compareAndSet(p1, p2);

        Person p3 = (Person) ar.get();
        System.out.println("p3 is " + p3);
        System.out.println("p3.equals(p1)=" + p3.equals(p1));
        System.out.println("p2.equals(p3)=" + p2.equals(p3));
    }


    public static void main(String[] args) throws InterruptedException {

        //testAtomicInteger();

        //testAtomicLongArray();

        testAtomicReference();

    }

}
