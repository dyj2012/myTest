package com.duyj2.work.collection;

import com.duyj2.work.Person;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        Person p1 = new Person("aris", 10);
        Person p2 = new Person("tom", 20);
        Person p3 = new Person("cuo", 30);
        Person p4 = new Person("ld", 35);
        Person p5 = new Person("ld", 35);

        Map map = new HashMap<Person, String>();

        map.put(p1, "");
        map.put(p2, "");
        map.put(p3, "");
        map.put(p4, "");
        map.put(p5, "");
        System.out.println(map.size());

    }
}
