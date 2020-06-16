package com.duyj.work.concurrent.collection;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArrayTest {

    private static List<String> list = new CopyOnWriteArrayList<String>();
    private static Set<String> set = new CopyOnWriteArraySet<String>();

    public static void main(String[] args) {

        // 同时启动两个线程对list进行操作！
        new MyThread("a").start();
        new MyThread("b").start();
    }

    private static void printList() {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            String value = (String) iter.next();
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    private static void printSet() {
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            String value = (String) iter.next();
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 10) {
                list.add(Thread.currentThread().getName() + i);
                set.add(Thread.currentThread().getName() + (i % 6));
            }
            // 通过“Iterator”遍历List。
            printList();
            printSet();
        }
    }
}
