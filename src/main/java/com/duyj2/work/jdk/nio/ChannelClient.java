package com.duyj2.work.jdk.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ChannelClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        int count = 0;
        while (count < 20) {
            count++;
            String message = "message " + count;
            byteBuffer.clear();
            byteBuffer.put(message.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            System.out.println(count + " client send: " + message);

            byteBuffer.clear();
            int readBytes = socketChannel.read(byteBuffer);
            if (readBytes > 0) {
                byteBuffer.flip();
                System.out.println(count + " client receive: " + getString(byteBuffer));
            }
        }

        socketChannel.close();
    }

    public static String getString(ByteBuffer buffer) {
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(buffer.asReadOnlyBuffer());
        return charBuffer.toString();
    }

}
