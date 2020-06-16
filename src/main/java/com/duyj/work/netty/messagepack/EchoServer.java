package com.duyj.work.netty.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * Created by LG on 2017/11/20.
 */
public class EchoServer {
    public static void main(String[] args) {
        new EchoServer().bind(8080);
    }

    public void bind(int port) {
        //配置服务端的线程组，一个用于服务端接收客户端连接，另一个进行SocketChannel的网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap用于启动NIO服务端的辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    //.handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));

                            ch.pipeline().addLast(new MsgpackDecoder());
                            //ch.pipeline().addLast(new JdkDecoder());

                            //在报文前增加2个字节，写消息长度
                            ch.pipeline().addLast(new LengthFieldPrepender(2));

                            ch.pipeline().addLast(new MsgpackEncoder());
                            //ch.pipeline().addLast(new JdkEncoder());

                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //绑定端口，sync为同步阻塞方法，等待绑定成功,ChannelFuture用于异步操作的通知回调
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("server started");
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("server shuting down");
            //
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
