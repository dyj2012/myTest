package com.duyj2.work.netty.httpfile;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by LG on 2017/11/21.
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/main/java/com/luangeng/netty/httpfile/";

    public static void main(String[] args) throws Exception {
        int port = 8080;
        String url = DEFAULT_URL;
        new HttpFileServer().run(port, url);
    }

    public void run(final int port, final String url) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast("http-decoder",
                                    new HttpRequestDecoder()); // http 请求消息解码器,
                            /*
                             * httpObject 解码器,
                             * 它的作用是将多个消息转换为单一的FullHttpRequest或FullHttpResponse
                             * 对象,原因是HTTP 解码器在每个HTTP消息中会生成多个消息对象 (
                             * HttpRequest/HttpResponse
                             * ,HttpContent,LastHttpContent)
                             */
                            ch.pipeline().addLast("http-aggregator",
                                    new HttpObjectAggregator(65536));
                            /*
                             * HTTP 响应消息编码器
                             */
                            ch.pipeline().addLast("http-encoder",
                                    new HttpResponseEncoder());
                            /*
                             * ChunkedWriteHandler
                             * 的主要作用是支持异步发送大的码流(例如大文件传输),但不占用过多的内存,防止JAVA内存溢出
                             */
                            ch.pipeline().addLast("http-chunked",
                                    new ChunkedWriteHandler());
                            /*
                             * 业务处理类
                             */
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler());
                        }
                    });
            ChannelFuture future = b.bind("localhost", port).sync();
            System.out.println("HTTP文件目录服务器启动，网址是 : " + "http://localhost:"
                    + port + url);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
