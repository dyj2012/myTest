package com.duyj.work.concurrent.locks;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by LG on 2017/8/16.
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                //当所有线程到达barrier时执行
                System.out.println("Barrier action");
            }
        });

        for (int k = 0; k < 4; k++) {
            final int n = k;
            new Thread(new Runnable() {
                private int id = n;
                @Override
                public void run() {
                    try {
                        System.out.println("thread " + id + " begin.");
                        TimeUnit.SECONDS.sleep(1);
                        //线程在这里等待，直到所有线程都到达barrier
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread " + id + " run over.");
                }
            }).start();
        }

        System.out.println("main end");

    }
}
