package com.duyj.work.netty.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;

import java.io.RandomAccessFile;

public class FileServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String path = "/Users/LG/Downloads/jenkins.war";
        RandomAccessFile file = new RandomAccessFile(path, "r");
        //ctx.write("L:" + file.length() + '\n');
        FileRegion region = new DefaultFileRegion(file.getChannel(), 0, file.length());
        ctx.writeAndFlush(region);
        file.close();

        //ctx.writeAndFlush("\n");
        System.out.println("send over");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
