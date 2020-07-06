package com.duyj2.work.concurrent.thread;

import com.duyj2.work.utils.Q;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CallableTest {

    static class Task implements Callable<String> {

        private int id;

        Task(int k) {
            id = k;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep((long) (1000 * Math.random() * id));
            return "return is " + id;
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService ser = Executors.newCachedThreadPool();

        ArrayList<Future<String>> rets = new ArrayList<Future<String>>();

        for (int k = 0; k < 8; k++) {
            Future<String> ret = ser.submit(new Task(k));
            rets.add(ret);
        }
        for (Future<String> fs : rets) {
            System.out.println(fs.get());
        }
        ser.shutdown();
        Q.p("main end");
    }

}
