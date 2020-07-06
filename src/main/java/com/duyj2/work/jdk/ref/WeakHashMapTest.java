package com.duyj2.work.jdk.ref;

import java.util.WeakHashMap;

/*

当key中的引用被gc掉之后，它会自动(类似自动)的方式将相应的entry给移除掉，即我们会看到size发生了变化
 */
public class WeakHashMapTest {

    public static void main(String[] args) throws InterruptedException {

        Object value = new Object();
        WeakHashMap<Object, Object> map = new WeakHashMap<>();

        Object key = new Object();
        map.put(key, value);
        key = null;

        System.out.println("map.size->" + map.size());

        System.gc();
        Thread.sleep(1000);

        System.out.println("map.size->" + map.size());
    }


}
