package com.duyj.work.netty.file;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class FileClientHandler extends ChannelInboundHandlerAdapter {

    String path = "/Users/LG/Downloads/jenkins2.war";

    RandomAccessFile file = null;

    long length = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "\n";
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
        file = new RandomAccessFile(path, "rw");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        length += ((ByteBuf) msg).readableBytes();
        System.out.println("recive : " + length);
        ByteBuffer bf = ((ByteBuf) msg).nioBuffer();
        file.getChannel().write(bf);
        if (length >= 73286753) {
            file.close();
        }
//        if(((ByteBuf) msg).readableBytes()==0){
//            file.close();
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
