package com.duyj2.work.concurrent.locks;

import com.duyj2.work.utils.Q;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * 当线程A调用Exchange对象的exchange()方法时会进入阻塞状态，直到线程B也调用了同一Exchange对象的exchange()方法，然后以线程安全的方式交换数据，之后线程A和B继续运行
 */
public class ExchangerTest {

    private static Exchanger<List<Integer>> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new A().start();
        new B().start();
    }

    static class A extends Thread {
        List<Integer> list = new ArrayList<>();

        @Override
        public void run() {
            Random rand = new Random();
            for (int i = 1; i < 5; i++) {
                list.add(i);
            }
            try {
                list = exchanger.exchange(list);
            } catch (InterruptedException e) {
            }
            Q.p("A end");
        }
    }

    static class B extends Thread {
        List<Integer> list = new ArrayList<>();

        @Override
        public void run() {
            try {
                list = exchanger.exchange(list);
            } catch (InterruptedException e) {
            }
            for (int a : list) {
                Q.p(a);
            }
            Q.p("B end");
        }
    }
}
