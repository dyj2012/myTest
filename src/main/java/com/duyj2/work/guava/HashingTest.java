package com.duyj2.work.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class HashingTest {

    public static String encryptMD5(final String msg) {
        return Hashing.md5().newHasher().putString(msg, Charset.defaultCharset()).hash().toString();
    }

    public static void main(String[] args) {

        Charset charset = Charset.defaultCharset();
        String str = Hashing.murmur3_32().newHasher().putString("test", charset).hash().toString();
        System.out.println(str);
        long a = Hashing.murmur3_32().newHasher().putString("test", charset).hash().padToLong();
        System.out.println(a);

        HashFunction hf = Hashing.murmur3_128();
        HashCode hc = hf.newHasher()
                //.putLong(1)
                .putString("test", Charsets.UTF_8)
                .hash();

        System.out.println(hc.toString());

        System.out.println(Hashing.murmur3_128().newHasher().putString("test", charset).hash().toString());
        System.out.println(Hashing.murmur3_128().newHasher().putString("test", charset).hash().padToLong());

        for (int i = 0; i < 100; i++) {
            System.out.println(Hashing.murmur3_128().newHasher(i).putString("test", charset).hash().padToLong());
        }

    }

}
