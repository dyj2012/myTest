package com.duyj2.work.concurrent.executor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService exe = null;

        // 5个线程集的线程池，跑完5个再跑5个的
        exe = Executors.newFixedThreadPool(5);

        // 1个线程的线程池，跑完1个再1个
        exe = Executors.newSingleThreadExecutor();

        for (int k = 0; k < 9; k++) {
            exe.execute(new MyRunnable());
        }

        //submit(Runnable) 方法返回一个 Future 对象 null
        Future future = exe.submit(new MyRunnable());
        System.out.println("1 future.get() = " + future.get());

        //
        future = exe.submit(new MyCallable());
        System.out.println("2 future.get() = " + future.get());

        future = exe.submit((Callable) () -> "Callable Result");
        System.out.println("3 future.get() = " + future.get());

        Set<MyCallable> callables = new HashSet<MyCallable>();
        callables.add(new MyCallable());
        exe.invokeAny(callables);
        exe.invokeAll(callables);

        exe.shutdown();
    }

    private static class MyRunnable implements Runnable {
        private static AtomicLong id = new AtomicLong(0);

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(this.getClass().getSimpleName() + id.incrementAndGet() + " end");
        }
    }

    private static class MyCallable implements Callable<String> {
        private static AtomicLong id = new AtomicLong(0);

        @Override
        public String call() throws Exception {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(this.getClass().getSimpleName() + id.incrementAndGet() + " end");
            return id + " end";
        }
    }

}
