package com.duyj2.work.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Servlet
 * Resource
 */
public class JettyTest
{

    public static void main( String[] args ) throws Exception {

        Server server = new Server();
        server.setStopAtShutdown(true);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setHost("localhost");
        connector.setPort(8080);
        connector.setReuseAddress(true);
        connector.setMaxIdleTime(3000);
        server.setConnectors(new Connector[] { connector });


        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/lg");
        //context.setResourceBase("./webapp");
        context.addServlet(DefaultServlet.class, "/");
        context.addServlet(new ServletHolder(DownloadServlet.class), "/download");
        context.addServlet(TestServlet.class, "/test/*");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase("./webapp");
        System.out.println(resource_handler.getResourceBase());

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{context, new DefaultHandler()});
        server.setHandler(handlers);

        server.start();
        server.dumpStdErr();
        server.join();
    }
}
