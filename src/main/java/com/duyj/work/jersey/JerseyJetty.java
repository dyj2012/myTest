package com.duyj.work.jersey;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyJetty {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        SelectChannelConnector serverConnector = new SelectChannelConnector();
        serverConnector.setPort(8080);

        ResourceConfig resourceConfig = new ResourceConfig(Hello.class);

        //Jersey类ServletContainer从HttpServlet继承,故可传入Jetty类ServletContextHandler.addServlet方法
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        server.addConnector(serverConnector);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(new ServletHolder(servletContainer), "/ws/*");
        server.setHandler(servletContextHandler);

        server.start();
        server.join();
    }

}
