package pduda.mvc;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.*;
import pduda.snake.manager.domain.ProgrammerMistake;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MvcServlet extends HttpServlet {
    private final Configuration freemarkerConfiguration;

    public MvcServlet() {
        this.freemarkerConfiguration = createFreemarkerConfiguration();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("tourneys")) {
            Template template = freemarkerConfiguration.getTemplate("tourneys.html");
            TourneysView view = new TourneysView(template, resp.getWriter());
            TourneysController controller = new TourneysController(view);
            controller.process();

        } else {
            Template template = freemarkerConfiguration.getTemplate("index.html");
            HomePageView view = new HomePageView(template, resp.getWriter());
            HomePageController controller = new HomePageController(view);
            controller.process();
        }
    }

    private Configuration createFreemarkerConfiguration() {
        Configuration c = new Configuration(new Version("1.0")); // TODO
        c.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/templates"));
        c.setDefaultEncoding("UTF-8");
        c.setLocale(Locale.getDefault());
        c.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        return c;
    }

    private static class HomePageController {
        private final HomePageView view;

        public HomePageController(HomePageView view) {
            this.view = view;
        }

        public void process() {
            view.display("snakez0r");
        }
    }

    private static class TourneysController {
        private final TourneysView view;

        public TourneysController(TourneysView view) {
            this.view = view;
        }

        public void process() {
            view.display();
        }
    }

    private static class HomePageView {
        private final Template template;
        private final Writer writer;

        public HomePageView(Template template, Writer writer) {
            this.template = template;
            this.writer = writer;
        }

        public void display(String appName) {
            Map<String, Object> data = new HashMap<>();
            data.put("appName", appName);

            try {
                template.process(data, writer);
                writer.flush();
            } catch (IOException e) {
                throw new ProgrammerMistake(e);
            } catch (TemplateException e) {
                throw new ProgrammerMistake(e);
            }
        }
    }

    private static class TourneysView {
        private final Template template;
        private final Writer writer;

        public TourneysView(Template template, Writer writer) {
            this.template = template;
            this.writer = writer;
        }

        public void display() {
            Map<String, Object> data = new HashMap<>();
            data.put("tourneys", Collections.emptyList());

            try {
                template.process(data, writer);
                writer.flush();
            } catch (IOException e) {
                throw new ProgrammerMistake(e);
            } catch (TemplateException e) {
                throw new ProgrammerMistake(e);
            }
        }
    }
}
