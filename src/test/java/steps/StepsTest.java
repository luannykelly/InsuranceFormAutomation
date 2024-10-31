package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InsurancePage;
import org.junit.Assert;
import support.BasePage;

import java.time.Duration;

public class StepsTest {
    private WebDriver driver;
    private BasePage basePage;
    private InsurancePage page;

    @Before
    public void setUp() {
        // Coloque o caminho correto do ChromeDriver aqui
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pessoal\\IdeaProjects\\insuranceFormAutomation\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        basePage = new BasePage(driver); // Criação da BasePage
    }

    @Given("i am on the page web")
    public void i_am_on_the_page_web() {
        // Forçar a abertura da URL correta
        String url = "http://sampleapp.tricentis.com/101/app.php";
        driver.get(url);

        // Espera para garantir que a URL correta esteja carregada
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(url)); // Espera até que a URL correta seja a ativa

        // Espera para garantir que um elemento da página esteja visível
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start-page-element-id"))); // Altere para um ID que exista na página

        page = new InsurancePage(driver); // Passando o driver para a InsurancePage
    }

    @When("i fill out the {string} section and press Next")
    public void i_fill_out_the_section_and_press_next(String section) {
        switch (section) {
            case "Enter Vehicle Data":
                page.fillOutEnterVehicleData();
                break;
            case "Enter Insurant Data":
                page.fillOutEnterInsurantData();
                break;
            case "Enter Product Data":
                page.fillOutEnterProductData();
                break;
            default:
                throw new IllegalArgumentException("Invalid section: " + section);
        }
    }

    @When("i select a price option and press Next")
    public void i_select_a_price_option_and_press_next() {
        page.fillOutSelectPriceOption();
    }

    @When("i enter my email on the {string} section and press Send")
    public void i_enter_my_email_on_the_section_and_press_send(String section) {
        if (section.equals("Send Quote")) {
            page.fillOutSendQuote();
        } else {
            throw new IllegalArgumentException("Invalid section: " + section);
        }
    }

    @Then("i should see the message {string} on screen")
    public void i_should_see_the_message_on_screen(String expectedMessage) {
        Boolean actualMessage = page.verifySuccessMessage(); // Presuma que você tenha um método que retorna a mensagem
        Assert.assertEquals("Expected success message not found on screen", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Limpa a referência do driver após encerrá-lo
        }
    }
}
