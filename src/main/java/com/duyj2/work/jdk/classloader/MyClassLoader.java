package com.duyj2.work.jdk.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by LG on 2017/12/6.
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader loader = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        Class<?> clazz = Class.forName("com.duyj2.work.jdk.classloader.ClassLoaderTestClass", true, loader);
        Object obj = clazz.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("target/classes/" + name.replace(".", File.separator) + ".class");
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassBytes(File file) throws Exception {
        // 使用字节流读入class文件
        FileInputStream fis = new FileInputStream(file);
        FileChannel ichannel = fis.getChannel();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel ochannel = Channels.newChannel(baos);

        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (true) {
            int i = ichannel.read(buf);
            if (i == 0 || i == -1)
                break;
            buf.flip();
            ochannel.write(buf);
            buf.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

}
