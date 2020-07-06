package com.duyj2.work.jdk.io;

import com.duyj2.work.utils.Q;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

/**
 * Created by LG on 2017/12/3.
 */
public class ZipTest {

    public static void unzip(String zipname) throws IOException {
        FileInputStream fis = new FileInputStream(zipname);
        CheckedInputStream cis = new CheckedInputStream(fis, new Adler32());
        ZipInputStream zis = new ZipInputStream(cis);
        BufferedInputStream bis = new BufferedInputStream(zis);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            PrintWriter out = new PrintWriter(new File(entry.getName() + "_new"));
            int x;
            while ((x = bis.read()) != -1) {
                out.append((char) x);
            }
            out.flush();
            out.close();
            zis.closeEntry();
        }
        Q.p("Checksum:" + cis.getChecksum().getValue());
        bis.close();
    }


    public static void zip(List<String> filepaths, String zipname) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipname);
        CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        BufferedOutputStream bos = new BufferedOutputStream(zos);

        for (String filepath : filepaths) {
            BufferedReader in = new BufferedReader(new FileReader(filepath));
            ZipEntry entry = new ZipEntry(filepath);
            zos.putNextEntry(entry);
            int c;
            while ((c = in.read()) != -1) {
                bos.write(c);
            }
            in.close();
            bos.flush();
            zos.closeEntry();
        }
        bos.close();
        Q.p("Checksum:" + cos.getChecksum().getValue());
    }

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("data.txt");

        zip(list, "data.zip");
        unzip("data.zip");

    }
}
