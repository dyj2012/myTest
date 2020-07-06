package com.duyj2.work.netty.protobuf;

import com.duyj2.work.utils.Q;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by LG on 2017/11/23.
 */
public class ProtoBufTest {

    //编码为byte数组
    private static byte[] encoder(PersonOuterClass.Person p) {
        return p.toByteArray();
    }

    //从byte数组解码
    private static PersonOuterClass.Person decoder(byte[] b) throws InvalidProtocolBufferException {
        return PersonOuterClass.Person.parseFrom(b);
    }

    //使用builder实例来设置属性
    private static PersonOuterClass.Person create() {
        PersonOuterClass.Person.Builder builder = PersonOuterClass.Person.newBuilder();
        builder.setAge(10);
        builder.setSex("man");
        builder.setUsername("luangeng");
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        PersonOuterClass.Person p = create();
        Q.p("before----------\n" + p.toString());

        PersonOuterClass.Person p2 = decoder(encoder(p));
        Q.p("afetr-----------\n" + p2.toString());
    }

}
