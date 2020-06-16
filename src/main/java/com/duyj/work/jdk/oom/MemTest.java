package com.duyj.work.jdk.oom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MemTest {

	public static void main(String[] args) throws InterruptedException {
		
		Thread.sleep(1000*60);

		int count = args.length == 1 ? Integer.parseInt(args[0]) : 30;

		List<List> list = new LinkedList<List>();
		long max = Runtime.getRuntime().maxMemory();
		long totle = Runtime.getRuntime().totalMemory();

		int i = 0;
		while (true) {
			list.add(new ArrayList<Integer>(512));

			while (i++ < count) {
				long free = Runtime.getRuntime().freeMemory();
				System.out.println("max:" + max / (1<<20) + "m  totle:" + totle/(1<<20) + "m   free=" + free/1024 + "kb");
				i = 0;
			}

		}
	}

}

