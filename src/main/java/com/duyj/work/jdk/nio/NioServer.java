package com.duyj.work.jdk.nio;

import com.duyj.work.utils.Q;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class NioServer {

    private static final Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException, InterruptedException {

        int count = 0;

        int msgValue = 0;

        //打开ServerSocketChannel，用于监听客户端的连接，是所有客户端连接的父管道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        //绑定监听端口，设置连接为非阻塞模式
        serverChannel.socket().bind(new InetSocketAddress(8081));
        serverChannel.configureBlocking(false);

        //创建Reactor线程，创建多路复用器并启动线程
        Selector selector = Selector.open();

        //将SercerSocketChannel注册到Reactor线程的多路复用器Selector上监听ACCEPT事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //多路复用器在线程run方法中无限循环体内轮询准备就绪的Key
            int num = selector.select();
            if (num == 0) {
                continue;
            }

            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                count++;
                SelectionKey key = (SelectionKey) it.next();

                if (key.isAcceptable()) {
                    //多路复用器监听到有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路
                    SocketChannel channel = serverChannel.accept();

                    //设置客户端链路为非阻塞状态
                    channel.configureBlocking(false);
                    channel.socket().setReuseAddress(true);

                    //将新接入的客户端连接注册到Reactor线程的多路复用器上，监听读操作，读取客户端发送的网络信息
                    channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    Q.p("accept: " + channel.getRemoteAddress());
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    //异步读取客户端请求消息到缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    buffer.flip();
                    String msg = charset.decode(buffer).toString();
                    Q.p(count + " Server receive: " + msg);
                    msgValue = Integer.valueOf(msg.trim()) + 1;
                    buffer.clear();
                }
                if (key.isValid() && key.isWritable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.write(charset.encode("" + msgValue));
                    Q.p(count + " Server send: " + msgValue);
                }

                Q.p();
                it.remove();
                TimeUnit.SECONDS.sleep(2);
            }
            //if (msgValue==100){
            //    break;
            //}
        }

        //关闭
        //selector.close();
        //serverChannel.close();
    }

    private void test() {

    }


}
