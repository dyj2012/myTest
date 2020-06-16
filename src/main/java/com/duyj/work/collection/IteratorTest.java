package com.duyj.work.collection;

import com.duyj.work.utils.Q;

import java.util.*;

public class IteratorTest {

    public static void main(String[] args) {
        iter();
        deleteFromList();
        addToList();
    }

    public static void iter() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        ListIterator itrlst = list.listIterator();
        while (itrlst.hasNext()) {
            System.out.println(itrlst.next());
        }

        Set<String> set = new HashSet<String>();
        set.add("set1");
        set.add("set2");
        set.add("set3");
        set.add("set4");
        Iterator<String> setIt = set.iterator();
        while (setIt.hasNext()) {
            System.out.println(setIt.next());
        }
    }

    public static void deleteFromList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("" + i);
        }
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String a = itr.next();
            if (Integer.valueOf(a) % 2 == 0) {
                itr.remove();
            }
        }
        Q.p(list);
    }

    public static void addToList() {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        ListIterator itrlst = list.listIterator();
        while (itrlst.hasNext()) {
            itrlst.next();
            //System.out.println("index1:" + (itrlst.nextIndex() - 1));
            itrlst.add(",");
            //System.out.println("index2:" + (itrlst.nextIndex() - 1));
        }
        Q.p(list);
    }


}
