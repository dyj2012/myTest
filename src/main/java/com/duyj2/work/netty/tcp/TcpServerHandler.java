package com.duyj2.work.netty.tcp;

import com.duyj2.work.utils.Q;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Created by LG on 2017/9/30.
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {

    private static int i = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.channel().writeAndFlush("Server收到:" + msg).addListener(new MyListener());
        Q.p("channel read");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private static class MyListener implements GenericFutureListener {
        @Override
        public void operationComplete(Future future) throws Exception {
            Q.p("MyListener " + future.get());
        }
    }

}
