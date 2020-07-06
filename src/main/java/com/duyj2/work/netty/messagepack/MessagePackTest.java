package com.duyj2.work.netty.messagepack;

import com.duyj2.work.netty.Person;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LG on 2017/11/22.
 */
public class MessagePackTest {

    public static void main(String[] args) throws IOException {
        MessagePackTest test = new MessagePackTest();
        test.test();
    }

    public void test1() throws IOException {
        List<String> src = new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        MessagePack msgpack = new MessagePack();
        // Serialize
        byte[] raw = msgpack.write(src);

        // Deserialize directly using a template
        List<String> dst1 = msgpack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst1.get(0));
        System.out.println(dst1.get(1));
        System.out.println(dst1.get(2));

        // Or, Deserialze to Value then convert type.
        Value dynamic = msgpack.read(raw);
        List<String> dst2 = new Converter(dynamic)
                .read(Templates.tList(Templates.TString));
        System.out.println(dst2.get(0));
        System.out.println(dst2.get(1));
        System.out.println(dst2.get(2));
    }

    public void test() throws IOException {

        List<String> src = new ArrayList<String>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        Person p = new Person();
        p.setAge(10);
        p.setBirth(new Date());
        p.setMan(true);
        p.setName("luangeng");
        p.setSon(p);
        p.setList(src);

        MessagePack msgpack = new MessagePack();
        //msgpack.register(Person.class);
        // Serialize
        byte[] raw = msgpack.write(src);

        Person p2 = msgpack.read(raw, Person.class);

        System.out.println(p2.toString());

    }

}
