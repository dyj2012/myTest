package com.duyj2.work.netty.tcp;

import com.duyj2.work.utils.Q;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;


/**
 * Created by LG on 2017/9/30.
 */
public class TcpClient {
    public static String HOST = "127.0.0.1";
    public static int PORT = 9000;

    public static final void start() {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap.group(group).channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast("handler", new TcpClientHandler());
                    //pipeline.addLast(new ReadTimeoutHandler(5)); //5秒后未与服务器通信，则断开连接。
                }
            });
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

            // Start the client.
            ChannelFuture f = bootstrap.connect(HOST, PORT).sync();
            Q.p("Client started.");

            for (int i = 1; i < 11; i++) {
                //Q.p(f.channel().isActive());
                f.channel().writeAndFlush("hello" + i).addListener(new MyListener());
                TimeUnit.SECONDS.sleep(1);
            }

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        TcpClient.start();
    }

    private static class MyListener implements ChannelFutureListener {
        @Override
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            Q.p("MyListener:" + channelFuture.get());
        }
    }

}
