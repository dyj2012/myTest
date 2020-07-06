package com.duyj2.work.jdk;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {

        String s = UUID.randomUUID().toString();
        System.out.println(s);

        s = UUID.fromString("3ca58726-5c26-46c8-b3e5-fe3bbf865429").toString();
        System.out.println(s);

        s = UUID.randomUUID().getLeastSignificantBits() + "";
        System.out.println(s);
    }
}
