package com.duyj2.work.netty.file;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;

public class FileClient {

    public static void main(String[] args) {
        new FileClient().connect("127.0.0.1", 9000);
    }

    public void connect(String host, int port) {
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new FileClientHandler());
                        }
                    });
            //发起异步连接操作，同步等待连接成功
            ChannelFuture future = bootstrap.connect(host, port).sync();
            System.out.println("client started");
            //等待客户端链路关闭
            //future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // System.out.println("client shuting down");
            //释放NIO线程组
            // group.shutdownGracefully();
        }
    }
}
