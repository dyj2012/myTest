package com.duyj2.work.jdk.nio.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LG on 2017/9/18.
 */
public class TalkServer {

    public static void main(String args[]) {

        ServerSocket server = null;
        Socket socket = null;
        BufferedReader is = null;
        PrintWriter os = null;

        try {
            //创建一个ServerSocket在端口4700监听客户请求
            server = new ServerSocket(4700);

            //使用accept()阻塞等待客户请求，有客户请求到来则产生一个Socket对象，并继续执行
            socket = server.accept();

            //由Socket对象得到输入流，并构造相应的BufferedReader对象
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //由Socket对象得到输出流，并构造PrintWriter对象
            os = new PrintWriter(socket.getOutputStream());

            String send = is.readLine();
            while (!send.equals("bye")) {
                os.println("got " + send);            //向客户端输出该字符串
                os.flush();                //刷新输出流，使Client马上收到该字符串
                send = is.readLine();
            }

        } catch (Exception e) {
            System.out.println("Error:" + e);
        } finally {
            os.close(); //关闭Socket输出流
            try {
                is.close(); //关闭Socket输入流
                socket.close(); //关闭Socket
                server.close(); //关闭ServerSocket
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
