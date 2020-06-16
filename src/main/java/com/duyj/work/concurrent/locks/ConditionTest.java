package com.duyj.work.concurrent.locks;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

	private static class MyBoundedBuffer {
		final Lock lock = new ReentrantLock();
		final Condition canPut = lock.newCondition();
		final Condition canTake = lock.newCondition();

		final int[] items = new int[15];
		int ptr=0;
		int count=0;

		public void put(int x) throws InterruptedException {
			lock.lock();
			try {
				while (count == items.length) {
					// 因该condition挂起当前线程，并且释放该condition的锁
					canPut.await();
				}
				items[ptr] = x;
				ptr++;
				count++;
				//System.out.println("put "+x);

				// 启动该condition下await的线程，该线程必须获得锁
				canTake.signal();
			} finally {
				lock.unlock();
			}
		}

		public Object take() throws InterruptedException {
			lock.lock();
			try {
				while (count == 0) {
					canTake.await();
				}
				ptr--;
				Object x = items[ptr];
				items[ptr] = 0;

				count--;
				//System.out.println("get "+x);
				canPut.signal();
				return x;
			} finally {
				lock.unlock();
			}
		}

		@Override
		public String toString() {
			return Arrays.toString(items);
		}
	}

	private static MyBoundedBuffer bf = new MyBoundedBuffer();
	static Random ra = new Random();

	// 生产者
	public static void Product() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						bf.put(2);
						Thread.sleep(10 * ra.nextInt(40));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// 消费者
	public static void Costom() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						bf.take();
						Thread.sleep(10 * ra.nextInt(45));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		Product();
		Costom();
		while (true) {
			System.out.println(bf);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
