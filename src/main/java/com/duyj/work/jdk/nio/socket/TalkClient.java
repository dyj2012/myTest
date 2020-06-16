package com.duyj.work.jdk.nio.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by LG on 2017/9/18.
 */
public class TalkClient {

    public static void main(String args[]) {
        Socket socket = null;
        PrintWriter os = null;
        BufferedReader is = null;

        try {
            //向本机的4700端口发出客户请求
            socket = new Socket("127.0.0.1", 4700);

            //由Socket对象得到输出流，并构造PrintWriter对象
            os = new PrintWriter(socket.getOutputStream());

            //由Socket对象得到输入流，并构造相应的BufferedReader对象
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //由系统标准输入设备构造BufferedReader对象
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

            String read;
            do {
                read = sin.readLine();
                os.println(read);              //将从系统标准输入读入的字符串输出到Server
                os.flush();                //刷新输出流，使Server马上收到该字符串
                System.out.println("Client send:" + read);                //在系统标准输出上打印读入的字符串
                System.out.println("Server response:" + is.readLine());   //从Server读入一字符串，并打印到标准输出上
            } while (!read.equals("bye"));

        } catch (Exception e) {
            System.out.println("Error" + e); //出错，则打印出错信息
        } finally {
            os.close(); //关闭Socket输出流
            try {
                is.close(); //关闭Socket输入流
                socket.close(); //关闭Socket
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
