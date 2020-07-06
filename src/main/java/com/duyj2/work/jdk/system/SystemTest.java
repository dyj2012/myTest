package com.duyj2.work.jdk.system;

import java.util.Map;
import java.util.Properties;

/**
 * Created by LG on 2017/12/3.
 */
public class SystemTest {

    public static void main(String[] args) {
        Properties p = System.getProperties();
        for (Map.Entry<Object, Object> entry : p.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("----------------------------------------");

        Map map = System.getenv();
        for (Object o : map.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) o;
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
