package com.duyj.work.reptile;

import com.duyj.work.utils.Q;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by LG on 2017/10/3.
 */
public class GetPhoto {

    private static Random r = new Random();

    // 根据url从网络获取网页文本
    public static Document getHtmlTextByUrl(String url) {
        Document doc = null;
        try {
            TimeUnit.MILLISECONDS.sleep(r.nextInt(100));
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").cookie("auth", "token").timeout(300000).get();
        } catch (IOException e) {
            try {
                doc = Jsoup.connect(url).userAgent("Mozilla/5.0").cookie("auth", "token").timeout(300000).get();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void go(int page) throws IOException {

        // 获取图片的绝对路径
        String gourl = "http://bing.plmeizi.com/?page=" + page;
        Document dochtml = getHtmlTextByUrl(gourl);
        Elements elements_a = dochtml.getElementsByClass("item");
        for (int x = 0; x < elements_a.size(); x++) {
            String pyotopage = elements_a.get(x).attr("href");
            Document dochtml_photo = getHtmlTextByUrl(pyotopage);
            Element elements_picurl = dochtml_photo.getElementById("picurl");
            String picurl = elements_picurl.attr("href");
            Element elements_searchlink = dochtml_photo.getElementById("searchlink");
            String name = elements_searchlink.getElementsByTag("span").get(0).html();
            name = name.split("\\(")[0].split(" ")[0];
            Q.p(name);

            if (picurl.contains("jpg")) {
                // 下载图片
                URL url_pic = new URL(picurl);
                DataInputStream dataInputStream = new DataInputStream(url_pic.openStream());
                String imageName = name + ".jpg";
                FileOutputStream fileOutputStream = new FileOutputStream(new File("temp" + File.separator + imageName));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
                dataInputStream.close();
                fileOutputStream.close();
            }
        }

    }
}
