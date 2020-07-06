package com.duyj2.work.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by LG on 2017/11/20.
 */
public class EchoClient {

    public static void main(String[] args) {
        new EchoClient().connect("127.0.0.1", 8080);
    }

    public void connect(String host, int port) {
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    //.option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //ByteBuf delimiter = Unpooled.copiedBuffer("§".getBytes());
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //ch.pipeline().addLast(new FixedLengthFrameDecoder(30));
                            //ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //发起异步连接操作，同步等待连接成功
            ChannelFuture future = bootstrap.connect(host, port).sync();
            ChannelFuture future2 = bootstrap.connect(host, port).sync();
            ChannelFuture future3 = bootstrap.connect(host, port).sync();
            System.out.println("client started");
            //等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("client shuting down");
            //释放NIO线程组
            group.shutdownGracefully();
        }
    }
}
