package com.duyj2.work.concurrent;

/**
 *ThreadLocal为每个线程拷贝一份副本，各自独立，每个线程只能获取和操作自己的那份副本
 */
public class ThreadLocalTest {

    // 用匿名内部类覆盖ThreadLocal的initialValue()方法来指定初始值
    private static ThreadLocal<Integer> env = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 10;
        }
    };

    private static int getAndIncrement() {
        int a = env.get();
        env.set(a + 1);
        return a;
    }

    public static void main(String[] args) {
        new TestClient().start();
        new TestClient().start();
        new TestClient().start();
    }

    private static class TestClient extends Thread {
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(getName() + " > " + ThreadLocalTest.getAndIncrement());
            }
        }
    }

}
