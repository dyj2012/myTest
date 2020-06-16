package com.duyj.work.netty.messagepack;

import com.duyj.work.netty.Person;
import com.duyj.work.utils.Q;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LG on 2017/11/20.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        List l = new ArrayList<String>();
        l.add("abc");
        l.add("123");
        Person p = new Person();
        p.setName("luangeng");
        p.setMan(true);
        p.setBirth(new Date());
        p.setList(l);
        for (int i = 0; i < 10; i++) {
            p.setAge(i);
            ctx.write(p);
        }
        ctx.flush();
    }

    //服务端返回应答信息后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Person) {
            Person p = (Person) msg;
            Q.p(p.toString());
        } else {
            Q.p(count++ + " client get: " + msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
