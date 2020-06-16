package com.duyj.work.jdk.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 读取文本文件
 */
public class ReadFile {

    public static String readFile(String filePath) throws Exception {

        // try open
        StringBuffer sb = new StringBuffer();
        String s;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filePath));

            // try read
            try {
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } catch (Exception e) {
                throw e;
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        } catch (FileNotFoundException e) {
            // can not find file, not opened so no close
            throw e;
        } catch (Exception e) {
            try {
                // maybe opened, try to close
                in.close();
            } catch (IOException e2) {
                // close failed, rare, exception chain
            }
            throw e;
        } finally {
            // empty, close can not be here
        }

        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        String str = readFile("data.txt");
        System.out.println(str);
    }

}
