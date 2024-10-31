package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasePage {
    protected WebDriver driver;
    private String chromeDriverPath = "C:\\Users\\Pessoal\\IdeaProjects\\insuranceFormAutomation\\src\\main\\resources\\chromedriver.exe";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void openUrl(String url) {
        driver.get(url);
        if (!driver.getCurrentUrl().equals(url)) {
            throw new RuntimeException("Failed to navigate to the correct URL: " + url);
        }
    }

    public void setChromeDriverPath(String path) {
        this.chromeDriverPath = path;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver.quit(); // Fecha o driver atual
        driver = new ChromeDriver(new ChromeOptions()); // Inicia um novo driver
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
