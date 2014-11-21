package pduda.snake.manager.web;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import pduda.snake.manager.domain.ProgrammerMistake;
import pduda.snake.manager.domain.usecase.BrowseTourneys;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SnakeServer {
    private Server server;
    private final BrowseTourneys browseTourneys;

    public SnakeServer(BrowseTourneys browseTourneys) {
        this.browseTourneys = browseTourneys;
    }

    public void start() {
        server = new Server(9999);
        HandlerCollection handlers = new HandlerCollection();
        handlers.addHandler(assetsHandler());
        handlers.addHandler(servletContextHandler());

        server.setHandler(handlers);
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException("Could not start the server", e);
        }
    }

    private ServletContextHandler servletContextHandler() {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/snake");
        handler.addServlet(new ServletHolder(new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                getHomePage(resp.getWriter());
                resp.getWriter().flush();
            }

            private void getHomePage(Writer writer) {
                long startTime = System.currentTimeMillis();
                Configuration cfg = new Configuration(new Version("1.0")); // TODO
                cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/templates"));
                cfg.setDefaultEncoding("UTF-8");
                cfg.setLocale(Locale.getDefault());
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

                try {
                    Template template = cfg.getTemplate("index.html");
                    Map<Object, Object> dataModel = new HashMap<>();
                    dataModel.put("appName", "Snakez0r");
                    template.process(dataModel, writer);
                    writer.flush();

                } catch (IOException e) {
                    throw new ProgrammerMistake(e);
                } catch (TemplateException e) {
                    throw new ProgrammerMistake(e);
                }

                long endTime = System.currentTimeMillis();
                System.out.println("Rendering time: " + (endTime - startTime));
            }
        }), "/*");
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
