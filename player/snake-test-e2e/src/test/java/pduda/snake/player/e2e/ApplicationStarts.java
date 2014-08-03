package pduda.snake.player.e2e;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ApplicationStarts {
    @Test
    public void applicationStarts() {
        TournamentOffice tournamentOffice = new TournamentOffice();

        tournamentOffice.canSeeApplicationHomePage();
    }

    private static class TournamentOffice {
        public void canSeeApplicationHomePage() {
            System.setProperty("webdriver.chrome.driver", chromeDriverLocation());
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("-incognito");
            ChromeDriver driver = new ChromeDriver(chromeOptions);

            try {
                driver.get("http://localhost:9999/snake");

                WebElement element = driver.findElement(By.cssSelector(".navbar-brand"));
                String appName = element.getText();

                assertEquals("Snake", appName);
            } finally {
                driver.quit();
            }
        }

        private String chromeDriverLocation() {
            String workDir = new File(".").getAbsolutePath();
            if (!workDir.contains("snake-test-e2e")) {
                return "snake-test-e2e/src/test/resources/chromedriver";
            } else {
                return "src/test/resources/chromedriver";
            }
        }
    }
}