package com.duyj.work.jdk.nio;

import com.duyj.work.utils.Q;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class NioClient {

    private static final int BUFFER_SIZE = 1024;

    private static final Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) throws IOException, InterruptedException {

        int count = 0;
        int msgValue = 0;

        //打开SocketChannel，绑定客户端本地地址（可选，默认系统会随机分配一个可用的本地地址）
        SocketChannel clientChannel = SocketChannel.open();

        //设置SocketChannel为非阻塞模式，同时设置客户端连接的TCP参数
        clientChannel.configureBlocking(false);

        Socket socket = clientChannel.socket();
        socket.setReuseAddress(true);
        socket.setReceiveBufferSize(BUFFER_SIZE);
        socket.setSendBufferSize(BUFFER_SIZE);

        //创建Reactor线程，创建多路复用器并启动线程
        Selector selector = Selector.open();

        //异步连接服务端
        boolean connected = clientChannel.connect(new InetSocketAddress("localhost", 8081));

        //向Reactor线程的多路复用器注册OP_CONNECT状态位，监听服务器端的TCP ACK应答
        clientChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            //多路复用器在线程run方法的无限循环体内轮询准备就绪的Key
            int num = selector.select();
            if (num == 0) {
                continue;
            }

            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                count++;
                SelectionKey key = (SelectionKey) it.next();

                //接收connect事件进行处理
                if (key.isConnectable()) {
                    //判断连接成功，如果成功，注册读事件到多路复用器
                    if (clientChannel.finishConnect()) {
                        Q.p("connect success");
                        //clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                }

                if (key.isReadable()) {
                    //异步读取客户端请求消息到缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    clientChannel.read(buffer);
                    buffer.flip();
                    String msg = charset.decode(buffer).toString();
                    Q.p(count + " Client receive: " + msg);
                    msgValue = Integer.valueOf(msg.trim()) + 1;
                    buffer.clear();
                }
                if (key.isValid() && key.isWritable()) {
                    //调用SocketChannel的异步write接口，将消息异步发送给客户端
                    clientChannel.write(charset.encode("" + msgValue));
                    Q.p(count + " Client send: " + msgValue);
                }

                Q.p();
                it.remove();
                TimeUnit.SECONDS.sleep(2);
            }
            if (msgValue == 20) {
                clientChannel.finishConnect();
                Q.p("finish");
                //break;
            }
        }

        //关闭连接
        //selector.close();
        //clientChannel.close();
    }

    private void test() {

    }


}
