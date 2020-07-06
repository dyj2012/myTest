package com.duyj2.work.java8;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*
 *
 * Base64是一种从二进制到文本字符串的编码方法，常用于在URL、Cookie、网页中传输少量二进制数据。
 * 原理：对二进制数据进行处理，每3个字节一组，一共是3x8=24bit，划为4组，每组正好6bit，2^6=64, 每组数字作索引，查表获得相应的4个字符，就是编码后的字符串
 * 把3字节的二进制数据编码为4字节的文本数据，长度增加33%，好处是编码后的文本数据可以在网页中直接显示
 * */
public class Base64s {

    public static void main(String[] args) {
        testCoder();
        testUrlCoder();
        testMimeCoder();
    }

    private static void testCoder() {
        Charset charset = StandardCharsets.UTF_8;
        final String text = "Base64 finally in Java 8! / as + 123";

        Base64.Encoder base64 = Base64.getEncoder();
        final String encoded = base64.encodeToString(text.getBytes(charset));
        System.out.println(encoded);

        Base64.Decoder decoder = Base64.getDecoder();
        final String decoded = new String(decoder.decode(encoded), charset);
        if (!decoded.equals(text)) {
            System.out.println("error");
        }
    }

    private static void testUrlCoder() {
        Charset charset = StandardCharsets.UTF_8;
        final String text = "Base64 finally in Java 8! / as + 123";

        Base64.Encoder base64 = Base64.getUrlEncoder();
        final String encoded = base64.encodeToString(text.getBytes(charset));
        System.out.println(encoded);

        Base64.Decoder decoder = Base64.getUrlDecoder();
        final String decoded = new String(decoder.decode(encoded), charset);
        if (!decoded.equals(text)) {
            System.out.println("error");
        }
    }

    private static void testMimeCoder() {
        Charset charset = StandardCharsets.UTF_8;
        final String text = "Base64 finally in Java 8! / as + 123";

        Base64.Encoder base64 = Base64.getMimeEncoder();
        final String encoded = base64.encodeToString(text.getBytes(charset));
        System.out.println(encoded);

        Base64.Decoder decoder = Base64.getMimeDecoder();
        final String decoded = new String(decoder.decode(encoded), charset);
        if (!decoded.equals(text)) {
            System.out.println("error");
        }
    }


}
