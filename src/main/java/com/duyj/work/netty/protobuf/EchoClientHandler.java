package com.duyj.work.netty.protobuf;

import com.duyj.work.utils.Q;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by LG on 2017/11/20.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    private static PersonOuterClass.Person create(int i) {
        PersonOuterClass.Person.Builder builder = PersonOuterClass.Person.newBuilder();
        builder.setAge(i);
        builder.setSex("man");
        builder.setUsername("luangeng" + i);
        return builder.build();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 20; i++) {
            ctx.writeAndFlush(create(i));
        }
    }

    //服务端返回应答信息后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PersonOuterClass.Person p = (PersonOuterClass.Person) msg;
        Q.p("Client get " + count++ + " :\n" + p.toString());
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
