package com.duyj2.work.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Webapp目录 形式
 */
public class JettyWebApp {

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        WebAppContext webAppContext = new WebAppContext("webapp", "/web");

        webAppContext.setDescriptor("webapp/WEB-INF/web.xml");
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setDisplayName("web");
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);

        try {
            server.start();
            System.out.println("server is start, port is " + port);
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
