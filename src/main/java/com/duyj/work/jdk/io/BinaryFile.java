package com.duyj.work.jdk.io;

import com.duyj.work.utils.Q;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 读取二进制文件
 */
public class BinaryFile {

    public static byte[] read(String filePath) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath).getAbsoluteFile()));

        try {
            byte[] data = new byte[bis.available()];
            bis.read(data);
            return data;
        } finally {
            bis.close();
        }

    }

    public static void main(String[] args) throws IOException {
        Q.p(read("data.txt"));
    }


}
