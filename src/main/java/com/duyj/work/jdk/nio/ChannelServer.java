package com.duyj.work.jdk.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class ChannelServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocketChannel.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress("localhost", 8080));
        int count = 0;
        SocketChannel socketChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (count < 20) {
            if (socketChannel == null) {
                socketChannel = serverSocketChannel.accept();
            } else {
                count++;
                byteBuffer.clear();
                socketChannel.read(byteBuffer);
                byteBuffer.flip();
                String message = getString(byteBuffer);
                System.out.println(count + " server received: " + message);

                byteBuffer.clear();
                byteBuffer.put(message.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                System.out.println(count + " server send: " + message);
            }
            TimeUnit.MILLISECONDS.sleep(500);
        }

        serverSocketChannel.close();
    }

    public static String getString(ByteBuffer buffer) {
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(buffer);
        return charBuffer.toString();
    }

}
