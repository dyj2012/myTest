package com.duyj.work.jdk.nio;

import com.duyj.work.utils.Q;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by LG on 2017/10/1.
 */
public class FileChannelTest {

    private static final int SIZE = 1024;

    private static final String encoding = System.getProperty("file.encoding");
    private static final Charset charset = Charset.forName(encoding);

    static void write(String filePath, String str) throws IOException {
        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer bf = ByteBuffer.wrap(str.getBytes(encoding));
        channel.write(bf);
        channel.close();
        outputStream.close();
    }

    static String read(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        channel.read(buffer);
        buffer.flip();
        String str = charset.decode(buffer).toString();
        channel.close();
        return str;
    }

    public static void copy(String path1, String path2) {
        FileChannel in = null, out = null;
        try {
            in = new FileInputStream(path1).getChannel();
            out = new FileOutputStream(path2).getChannel();
            ByteBuffer bf = ByteBuffer.allocate(SIZE);
            while (in.read(bf) != -1) {
                bf.flip();
                out.write(bf);
                bf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Q.p(encoding);
        Q.p(charset);

        String str = "123 456 abcd EFG !@#$% 测试\n dier ssss";
        write("data.txt", str);

        String str2 = read("data.txt");
        System.out.print(str2);

        //copy("data.txt","data2.txt");
    }
}
