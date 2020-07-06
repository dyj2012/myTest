package com.duyj2.work.java8;

import com.duyj2.work.Person;
import com.duyj2.work.utils.Q;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamTest {

    public static void main(String[] args) {

        //Person p = Person::new;

        Person p1 = new Person("aris", 10);
        Person p2 = new Person("tom", 20);
        Person p3 = new Person("cuo", 30);
        Person p4 = new Person("ld", 35);
        Person p5 = new Person("asd", 40);

        List<Person> list = new ArrayList<Person>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        list.stream().skip(1).limit(10).filter(e -> e.getAge() > 25).forEach(e -> Q.p(e));
//        list.stream().


        List list2 = Stream.of(p1, p2, p3).collect(toList());
        Q.p(list2);

        List<String> collects1 = list.stream().map(e -> e.getName()).collect(toList());
        List<String> collects2 = list.stream().map(Person::getName).collect(toList());
        Q.p(collects1);
        Q.p(collects2);

        List<String> strArr = Stream.of("a", "b", "c").map(s -> s.toUpperCase()).collect(toList());
        Q.p(strArr);


        int sum = Stream.of(1, 2, 3, 4, 5).reduce(0, (acc, element) -> acc + element);
        Q.p(sum);


    }

}
