package com.duyj2.work.java8;

import com.duyj2.work.utils.Q;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LambdaTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Arrays.asList("a", "b", "c").forEach(e -> Q.p(e));
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));

        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });

        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList("a", "b", "d").sort(String::compareTo);
        Arrays.asList("a", "b", "d").sort(Comparator.naturalOrder());
        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            return e1.compareTo(e2);
        });


        Q.p();
        ExecutorService exe = Executors.newFixedThreadPool(1);
        exe.execute(() -> Q.p("Runnable " + Thread.currentThread().getName()));
        Future<String> sf = exe.submit(() -> "Callable " + Thread.currentThread().getName());
        Q.p(sf.get());
        exe.shutdown();

    }

}
