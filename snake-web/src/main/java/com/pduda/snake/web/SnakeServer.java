package com.pduda.snake.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;
import java.net.URL;

public class SnakeServer {
    private Server server;

    public void start() {
        server = new Server(9999);
        HandlerCollection handlers = new HandlerCollection();
//        handlers.addHandler(servletContextHandler());
        handlers.addHandler(staticContentHandler());
//
//        ServletContextHandler handler = new ServletContextHandler();
//        handler.setContextPath("snake");
//        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig())), "/*");
        server.setHandler(handlers);
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException("Could not start the server", e);
        }
    }


    private ServletContextHandler servletContextHandler() {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/snake/rest");
        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig())), "/*");
        return handler;
    }

    private ServletContextHandler staticContentHandler() {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/snake");
        handler.addServlet(new ServletHolder(new DefaultServlet()), "/*");
//        final FilterHolder filterHolder = new FilterHolder(new UrlRewriteFilter());
//        handler.addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD) );
        handler.setResourceBase(resourceBase());
        handler.setClassLoader(Thread.currentThread().getContextClassLoader());
//        handler.setWelcomeFiles(new String[]{"index.html"});
        return handler;
    }

    private String resourceBase() {
        URL webapp = this.getClass().getClassLoader().getResource("webapp");
        if (webapp != null) {
            System.out.println("*** Running distribution");
            return webapp.toExternalForm();
        } else {
            System.out.println("*** Running from ide");
            String absolutePath = new File(".").getAbsolutePath();
            String result = absolutePath.substring(0, absolutePath.indexOf("snake/") + "snake/".length());
            return result + "snake-web/src/main/resources/webapp";
        }
    }

    private ResourceConfig resourceConfig() {
        return new ResourceConfig();
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException("Could not stop the server", e);
        }
    }

    public void join() {
        try {
            server.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Could not join the thread", e);
        }
    }
}
