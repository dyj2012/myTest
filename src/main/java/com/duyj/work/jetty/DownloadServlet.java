package com.duyj.work.jetty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * Created by LG on 2017/7/12.
 */
public class DownloadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        //PrintWriter pw = null;
        FileInputStream in = null;
        OutputStream outs = null;

        //String path = request.getParameter("filepath");
        String path = "/Users/LG/test.txt";
        //path = new String(fileName.getBytes("iso8859-1"),"UTF-8");

        try {
            File file = new File(path);
            if(!file.exists())
            {
                response.setStatus(404);
                //pw.println("file not found!");
                //request.getRequestDispatcher("/err.jsp").forward(request, response);
                return;
            }
            response.setContentType("text/html");
            String realname = path.substring(path.indexOf("/")+1);
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            in = new FileInputStream(path);
            outs = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                outs.write(buffer, 0, len);
            }

            System.out.println(response.getStatus());
        }
        catch(Exception e)
        {
            //pw.println(e.getMessage());
            response.setStatus(300);
            //log
        }
        finally {
            response.setStatus(200);
            try {
                if(outs != null) {
                    outs.flush();
                    outs.close();
                }
                if(in != null) {
                    in.close();
                }

            } catch (IOException e) {
            }
//            if(pw!=null) {
//                pw.flush();
//                pw.close();
//            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
