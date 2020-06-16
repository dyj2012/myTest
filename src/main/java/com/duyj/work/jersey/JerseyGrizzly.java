package com.duyj.work.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JerseyGrizzly {

    public static final String ROOT_PATH = "helloworld";
    private static final URI BASE_URI = URI.create("http://localhost:8080/base/");

    public static void main(String[] args) {
        try {
            System.out.println("\"Hello World\" Jersey Example App");

            final ResourceConfig resourceConfig = new ResourceConfig(Hello.class);
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            server.start();

            System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
                    BASE_URI, ROOT_PATH));
            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(JerseyGrizzly.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
