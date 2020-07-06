package com.duyj2.work.java8;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        String str = "abc";
        Optional<String> optional = Optional.of(str);

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }
}
