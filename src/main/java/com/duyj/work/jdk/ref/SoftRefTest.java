package com.duyj.work.jdk.ref;

import java.util.Map;
import java.util.WeakHashMap;

//SoftReference 会尽可能长的保留引用直到 JVM 内存不足时才会被回收,适合做缓存
public class SoftRefTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {

        Object value = new Object();
        Map<Object, Object> map = new WeakHashMap<>();

        for (int i = 0; i < 20; i++) {

            byte[] bytes = new byte[_1M];
            map.put(bytes, value);
            System.out.println("map.size->" + map.size());
        }
    }

}
