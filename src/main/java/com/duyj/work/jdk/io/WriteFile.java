package com.duyj.work.jdk.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 写文本文件
 */
public class WriteFile {

    public static void write(String filePath, String text) {
        try{
            PrintWriter out = new PrintWriter(new FileWriter(filePath), true);
            try{
				out.print(text);
			}finally{
				out.close();
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	

	public static void main(String[] args) throws IOException{
        String file = "data.txt";
        //print current path
		System.out.println(new File("").getAbsolutePath());

        String context = "i am context, 1234567890!@#$%^&*()123";
        write(file, context);
	}

}
