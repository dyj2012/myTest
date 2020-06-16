package com.duyj.work.reptile;

import com.duyj.work.utils.Q;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by LG on 2017/10/3.
 */
public class ReptileBing {

    private static int N = 47;

    private static CountDownLatch latch = new CountDownLatch(N);

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 1; i <= N; i++) {
            new MyThread(i).start();
            TimeUnit.MILLISECONDS.sleep(5000);
        }
        latch.await();
        Q.p("main end");
    }

    private static class MyThread extends Thread {
        private int page;

        public MyThread(int page) {
            this.page = page;
        }

        @SuppressWarnings("static-access")
        @Override
        public void run() {
            GetPhoto getPhoto = new GetPhoto();
            try {
                getPhoto.go(page);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }

}
