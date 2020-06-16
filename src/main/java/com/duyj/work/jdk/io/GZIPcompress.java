package com.duyj.work.jdk.io;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {

    public static void compress(String file, String outgz) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedOutputStream bs = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(outgz)));

		int c;
		while ((c = br.read()) != -1) {
			bs.write(c);
		}
		br.close();
		bs.close();
	}

    public static String unCompress(String filegz) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(filegz))));
		String s;
        StringBuilder sb = new StringBuilder();
        while ((s = bf.readLine()) != null) {
			sb.append(s);
		}
		bf.close();
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
        String fileOut = "data.gz";
        String in = "data.txt";

        compress(in, fileOut);
        String out = unCompress(fileOut);

        System.out.println(out);
	}

}
