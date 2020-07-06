package com.duyj2.work.netty.messagepack;

import com.duyj2.work.netty.Person;
import com.duyj2.work.utils.Q;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by LG on 2017/11/20.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Person) {
            Person p = (Person) msg;
            Q.p(p.toString());
        } else {
            System.out.println("The server received(" + count++ + "): " + msg);
        }

        ctx.writeAndFlush(msg);//异步发送
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
