package com.duyj.work.utils;

import com.sun.deploy.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Q {

    public static void p() {
        System.out.println();
    }

    public static <T> void p(T msg) {
        System.out.println(String.valueOf(msg));
    }

    public static <T> void p(T[] ss) {
        //Arrays.toString(ss);
        int i = 0;
        for (T t : ss) {
            System.out.println("[" + i++ + "]->" + String.valueOf(t));
        }
    }

    public static <T> void p(Collection<T> list) {
        System.out.print("[" + StringUtils.join(list, ",") + "]");
    }

    public static <K, V> void p(Map<K, V> map) {
        Set<Map.Entry<K, V>> entrys = map.entrySet();
        for (Map.Entry<K, V> entry : entrys) {
            System.out.print("{" + entry.getKey() + " : " + entry.getValue() + "}  ");
        }
        p();
    }


}
