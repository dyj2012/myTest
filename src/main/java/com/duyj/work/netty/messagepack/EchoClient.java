package com.duyj.work.netty.messagepack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

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
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));

                            ch.pipeline().addLast(new MsgpackDecoder());
                            //ch.pipeline().addLast(new JdkDecoder());

                            ch.pipeline().addLast(new LengthFieldPrepender(2));

                            ch.pipeline().addLast(new MsgpackEncoder());
                            //ch.pipeline().addLast(new JdkEncoder());

                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //发起异步连接操作，同步等待连接成功
            ChannelFuture future = bootstrap.connect(host, port).sync();
            System.out.println("client started");
            //等待客户端链路关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("client shuting down");
            //
            group.shutdownGracefully();
        }
    }
}
