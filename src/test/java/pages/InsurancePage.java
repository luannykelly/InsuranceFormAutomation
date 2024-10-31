package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InsurancePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public InsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tempo de espera
    }

    public void fillOutEnterVehicleData() {
        waitForElement(By.id("make"));
        new Select(driver.findElement(By.id("make"))).selectByVisibleText("Ford");
        driver.findElement(By.id("engineperformance")).sendKeys("90");
        driver.findElement(By.id("dateofmanufacture")).sendKeys("12/03/2020");
        new Select(driver.findElement(By.id("numberofseats"))).selectByVisibleText("5");
        new Select(driver.findElement(By.id("fuel"))).selectByVisibleText("Petrol");
        driver.findElement(By.id("listprice")).sendKeys("5000");
        driver.findElement(By.id("licenseplatenumber")).sendKeys("KTY4678");
        driver.findElement(By.id("annualmileage")).sendKeys("5000");
        driver.findElement(By.id("nextenterinsurancedata")).click();
    }

    public void fillOutEnterInsurantData() {
        waitForElement(By.id("firstname"));
        driver.findElement(By.id("firstname")).sendKeys("John");
        driver.findElement(By.id("lastname")).sendKeys("Whitehorse");
        driver.findElement(By.id("birthdate")).sendKeys("09/12/1980");
        driver.findElement(By.id("gendermale")).click();
        driver.findElement(By.id("streetaddress")).sendKeys("1600 Fake Street");
        new Select(driver.findElement(By.id("country"))).selectByVisibleText("United States");
        driver.findElement(By.id("zipcode")).sendKeys("94043");
        driver.findElement(By.id("city")).sendKeys("Mountain View");
        new Select(driver.findElement(By.id("occupation"))).selectByVisibleText("Selfemployed");
        driver.findElement(By.id("speeding")).click();
        driver.findElement(By.id("website")).sendKeys("www.johnwhitehorseshoes.com");
        driver.findElement(By.id("nextenterproductdata")).click();
    }

    public void fillOutEnterProductData() {
        waitForElement(By.id("startdate"));
        driver.findElement(By.id("startdate")).sendKeys("12/01/2024");
        new Select(driver.findElement(By.id("insurancesum"))).selectByVisibleText("30.000.000,00");
        new Select(driver.findElement(By.id("meritrating"))).selectByVisibleText("Bonus 7");
        new Select(driver.findElement(By.id("damageinsurance"))).selectByVisibleText("Full Coverage");
        driver.findElement(By.id("legaldefenseinsurance")).click();
        new Select(driver.findElement(By.id("courtesycar"))).selectByVisibleText("Yes");
        driver.findElement(By.id("nextselectpriceoption")).click();
    }

    public void fillOutSelectPriceOption() {
        waitForElement(By.id("selectplatinum"));
        driver.findElement(By.id("selectplatinum")).click();
        driver.findElement(By.id("nextsendquote")).click();
    }

    public void fillOutSendQuote() {
        waitForElement(By.id("email"));
        driver.findElement(By.id("email")).sendKeys("test@example.us");
        driver.findElement(By.id("phone")).sendKeys("6019521325");
        driver.findElement(By.id("username")).sendKeys("johnw");
        driver.findElement(By.id("password")).sendKeys("John123");
        driver.findElement(By.id("confirmpassword")).sendKeys("John123");
        driver.findElement(By.id("sendemail")).click();
    }

    public boolean verifySuccessMessage() {
        waitForElement(By.xpath("/html/body/div[4]/h2"));
        WebElement message = driver.findElement(By.xpath("/html/body/div[4]/h2"));
        return message.getText().equals("Sending e-mail success");
    }

    public boolean verifyUserPlaceholder() {
        waitForElement(By.className("sa-placeholder"));
        WebElement placeholder = driver.findElement(By.className("sa-placeholder"));
        return placeholder.isDisplayed();
    }

    private void waitForElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
