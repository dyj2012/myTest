package com.duyj.work.jdk.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoader {

    public static void loadJar(String jarPath) {
        try {
            //根据url1创建类装载器
            URL url = new URL("file:" + jarPath);
            URLClassLoader loader = new URLClassLoader(
                    new URL[]{url}, Thread.currentThread().getContextClassLoader());
            Class clazz = loader.loadClass("Test");
            Object obj = clazz.newInstance();
            System.out.println(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        loadJar("temp/test.jar");
    }

}
