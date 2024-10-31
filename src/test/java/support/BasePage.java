package support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Atualiza a URL esperada para incluir 'https'
        String expectedUrl = url.startsWith("http://") ? url.replace("http://", "https://") : url;
        String currentUrl = driver.getCurrentUrl();

        // Verifica se a URL atual é a URL esperada
        if (!currentUrl.equals(expectedUrl)) {
            // Lança uma exceção com a URL atual
            throw new RuntimeException("Failed to navigate to the correct URL: " + expectedUrl + ". Current URL: " + currentUrl);
        }
    }



    public void clickElementById(String elementId) {
        WebElement element = driver.findElement(By.id(elementId));
        element.click();
    }

    public WebElement waitForElementById(String elementId, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
    }

    public void setChromeDriverPath(String path) {
        this.chromeDriverPath = path;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        if (driver != null) {
            driver.quit();
        }
        driver = new ChromeDriver(new ChromeOptions());
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
