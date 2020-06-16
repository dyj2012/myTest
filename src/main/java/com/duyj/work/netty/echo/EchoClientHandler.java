package com.duyj.work.netty.echo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by LG on 2017/11/20.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {


    private static int count = 0;

    //当客户端和服务端TCP连接建立后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new MyThread(ctx).start();
    }

    //服务端返回应答信息后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println(count++ + "  " + this.hashCode() + " client received: " + body);
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
