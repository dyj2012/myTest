package com.duyj.work.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by LG on 2017/10/1.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        byte[] request = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(request);
//        String body = new String(request, "UTF-8");
        String body = (String) msg;
        System.out.println("The time server received(" + count++ + "): " + body);

        String currentTime = new Date().toString();
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf response = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(response);//异步发送,放在缓冲区
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // ctx.flush();//将缓冲区消息全部写到SocketChannel中
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
