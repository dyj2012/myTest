package com.duyj.work.netty.tcp;

import com.duyj.work.utils.Q;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by LG on 2017/9/30.
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Q.p("client收到:" + msg);
    }

}
