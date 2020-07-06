package com.duyj2.work.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by LG on 2017/10/1.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    //当客户端和服务端TCP连接建立后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] request = ("Now Time" + System.getProperty("line.separator")).getBytes();
        for (int i = 0; i < 20; i++) {
            ByteBuf msg = Unpooled.buffer(request.length);
            msg.writeBytes(request);
            ctx.writeAndFlush(msg);//发送请求
        }
    }

    //服务端返回应答信息后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte[] request = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(request);
//        String body = new String(request, "UTF-8");
        String body = (String) msg;
        System.out.println("Now is(" + count + "):" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
