package pduda.snake.manager.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import pduda.mvc.MvcServlet;
import pduda.snake.manager.domain.usecase.BrowseTourneys;

import java.io.File;
import java.net.URL;

public class JettySnakeServer {
    private Server server;
    private final BrowseTourneys browseTourneys;

    public JettySnakeServer(BrowseTourneys browseTourneys) {
        this.browseTourneys = browseTourneys;
    }

    public void start() {
        server = new Server(9999);
        HandlerCollection handlers = new HandlerCollection();
        handlers.addHandler(assetsHandler());
        handlers.addHandler(appHandler());

        server.setHandler(handlers);
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException("Could not start the server", e);
        }
    }

    private ServletContextHandler appHandler() {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/snake");
        handler.addServlet(new ServletHolder(new MvcServlet(browseTourneys)), "/*");
        return handler;
    }

    private ServletContextHandler assetsHandler() {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/assets");
        handler.addServlet(new ServletHolder(new DefaultServlet()), "/*");
        handler.setResourceBase(assetsBase());
        handler.setClassLoader(Thread.currentThread().getContextClassLoader());
        return handler;
    }

    private String assetsBase() {
        String resourceBase = findAssetsBase();
        System.out.println("*** Resource base: " + resourceBase);
        return resourceBase;
    }

    private String findAssetsBase() {
        URL assets = this.getClass().getClassLoader().getResource("assets");
        if (assets != null) {
            System.out.println("*** Running distribution");
            return assets.toExternalForm();
        } else {
            System.out.println("*** Running from ide");
            String absolutePath = new File(".").getAbsolutePath();
            String result = absolutePath.substring(0, absolutePath.indexOf("manager/") + "manager/".length());
            return result + "manager-web/src/main/resources/assets";
        }
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
