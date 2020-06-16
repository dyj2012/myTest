package com.duyj.work.netty.echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {

    private String msg;

    private ChannelHandlerContext ctx;

    public MyThread(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void run() {
        msg = "hello    \n ";
        int i = 20;
        while (i-- > 0) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
