package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.InsurancePage;
import org.junit.Assert;
import support.BasePage;

public class StepsTest {
    private WebDriver driver;
    private BasePage basePage;
    private InsurancePage page;

    @Before
    public void setUp() {
        basePage = new BasePage(driver);
        basePage.setUp();
        driver = basePage.getDriver();
        page = new InsurancePage(driver);
    }

    @Given("i am on the page web")
    public void i_am_on_the_page_web() {
        String url = "http://sampleapp.tricentis.com/101/app.php";
        basePage.openUrl(url);
    }

    @Given("i am on the page form")
    public void i_am_on_the_page_form() {
        basePage.clickElementById("nav_automobile");  // Clica no botão de veículo

        basePage.waitForElementById("start-page-element-id", 10);
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
        String actualMessage = String.valueOf(page.verifySuccessMessage());
        Assert.assertEquals("Expected success message not found on screen", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        basePage.closeDriver();
        driver = null;
    }
}
