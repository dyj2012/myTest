package com.duyj.work.jdk.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG on 2017/2/14.
 */
public class OOMTest {


//VM args: -Xms20m   -Xmx20m  -XX:+HeapDumpOnOutOfMemoryError

    public static void main(String[] args) throws Exception {
        int num = 1000;
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
            System.out.print(i);

        }
    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }


}
