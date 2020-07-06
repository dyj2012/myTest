package com.duyj2.work.jdk.system;

import com.duyj2.work.utils.Q;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by LG on 2017/12/3.
 */
public class RuntimeTest {

    public static void main(String[] args) throws IOException {

        long M = 1024 * 1024;
        long G = M * 1024;

        Runtime runtime = Runtime.getRuntime();
        ShutDownThread t = new ShutDownThread();
        runtime.addShutdownHook(t);
        //runtime.removeShutdownHook(t);

        Q.p("availableProcessors: " + runtime.availableProcessors());

        Q.p("freeMemory: " + runtime.freeMemory() / M);
        Q.p("maxMemory: " + runtime.maxMemory() / M);
        Q.p("totalMemory: " + runtime.totalMemory() / M);

        Process process = runtime.exec("date");
        InputStream is = process.getInputStream();
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        //runtime.load("data.txt");
        //runtime.loadLibrary("data.txt");


        runtime.traceInstructions(false);
        runtime.traceMethodCalls(false);

    }

    private static class ShutDownThread extends Thread {
        public void run() {
            System.out.println("shut down");
        }
    }
}
