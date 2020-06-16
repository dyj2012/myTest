package com.duyj.work.jdk.io;

import java.io.*;

/**
 * Created by LG on 2017/11/15.
 */
public class ListClass {

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File(".");

        //抽象类
        InputStream is = null;

        is = new FileInputStream(f);

        is = new ByteArrayInputStream("abc123".getBytes());

        //FilterInputStream子类
        is = new DataInputStream(is);

        is = new BufferedInputStream(is);


        //抽象类
        OutputStream os = null;

        os = new ByteArrayOutputStream();

        os = new FileOutputStream(f);

        //FilterOutputStream子类
        os = new DataOutputStream(os);

        os = new BufferedOutputStream(os);

        //stream转Reader和Writer
        Reader r = new InputStreamReader(is);
        Writer w = new OutputStreamWriter(os);






    }
}
