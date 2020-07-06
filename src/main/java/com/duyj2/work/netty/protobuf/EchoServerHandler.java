package com.duyj2.work.netty.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by LG on 2017/11/20.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PersonOuterClass.Person p = (PersonOuterClass.Person) msg;
        System.out.println("Server received " + count++ + " :\n" + p.toString());
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
