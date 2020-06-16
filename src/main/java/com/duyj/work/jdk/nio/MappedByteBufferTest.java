package com.duyj.work.jdk.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {

    private static final int SIZE = 1024 * 50 * 1024;
    private static String str1 = "/Users/LG/Downloads/[电影天堂www.dy2018.com]神偷奶爸3BD中英双字.rmvb";
    private static String str2 = "/Users/LG/Downloads/[电影天堂www.dy2018.com]神偷奶爸3BD中英双字2.rmvb";
    private static String str3 = "/Users/LG/Downloads/[电影天堂www.dy2018.com]神偷奶爸3BD中英双字3.rmvb";

    public static void main(String[] args) throws Exception {
        test1();
        test2();
    }

    public static void test1() throws IOException {
        long timeStar = System.currentTimeMillis();

        ByteBuffer byteBuf = ByteBuffer.allocate(SIZE);
        FileInputStream fis = new FileInputStream(str1);
        FileOutputStream fos = new FileOutputStream(str2);
        FileChannel fc = fis.getChannel();
        System.out.println("SIZE " + fc.size());

        while (fc.read(byteBuf) > -1) {
            byteBuf.flip();
            fos.getChannel().write(byteBuf);
            byteBuf.clear();
        }

        fos.flush();
        fos.close();
        fis.close();
        long timeEnd = System.currentTimeMillis();
        System.out.println("cost time :" + (timeEnd - timeStar) + "ms");
    }

    public static void test2() throws IOException {
        long timeStar = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream(str1);
        FileOutputStream fos = new FileOutputStream(str3);
        FileChannel fc = fis.getChannel();
        System.out.println("SIZE " + fc.size());
        MappedByteBuffer mbb;
        long length = fc.size();
        long k = 0;
        while (k < length) {
            long size = Math.min(SIZE, length - k);
            mbb = fc.map(FileChannel.MapMode.READ_ONLY, k, size);
            //mbb.flip();
            fos.getChannel().write(mbb);
            mbb.clear();
            k = k + SIZE;
        }

        fos.flush();
        fos.close();
        fis.close();
        long timeEnd = System.currentTimeMillis();
        System.out.println("cost time :" + (timeEnd - timeStar) + "ms");
    }

}
