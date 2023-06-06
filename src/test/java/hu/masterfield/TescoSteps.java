package hu.masterfield;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class TescoSteps {

    protected static WebDriver driver;

    protected static WebDriverWait wait;

    @Before // cucumber annotáció
    public static void setup() throws IOException {
        WebDriverManager.chromedriver().setup();

        // loading arguments, properties
        Properties props = new Properties(); // java.util
        InputStream is = TescoSteps.class.getResourceAsStream("/browser.properties");
        props.load(is);

        // set chrome options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(props.getProperty("chrome.arguments"));
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        //chromeOptions.setHeadless(true);

        // init driver
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().setSize(new Dimension(900, 900)); // ...selenium.Dimension
    }

    @After
    public static void cleanup() {
        driver.quit();
    }

    @Given("open main page")
    public void openMainPage() {
        driver.get("https://bevasarlas.tesco.hu/groceries/");
    }

    @And("accept cookies")
    public void acceptCookies() throws InterruptedException {
        ////*[@id="sticky-bar-cookie-wrapper"]/span/div/div/div[2]/form[1]/button
        ///html/body/div[1]/div/div/div[1]/div/span/div/div/div[2]/form[1]/button
        WebElement acceptButton = wait.until(driver -> driver.findElement(By.xpath("//*[@id=\"sticky-bar-cookie-wrapper\"]/span/div/div/div[2]/form[1]/button")));
        acceptButton.click();
    }

//*[@id="utility-header-language-switch-link"]/span/span

    @Given("language is set to {string}")
    public void languageIsSetTo(String lang ) throws InterruptedException {

        WebElement langButton = driver.findElement(By.xpath("//*[@id=\"utility-header-language-switch-link\"]/span/span"));

        if(langButton.getText().equals("Magyar") && lang.equals("magyar")) {
            langButton.click();

        }
        else if(langButton.getText().equals("English") && lang.equals("english")) {
            langButton.click();

        }
    }

    @When("change the language to {string}")
    public void changeTheLanguageTo(String newLang) throws InterruptedException {
        languageIsSetTo(newLang);
    }

    @Then("it shows elements in {string}")
    public void itShowsElementsIn(String newLang) {
        WebElement langButton = driver.findElement(By.xpath("//*[@id=\"utility-header-language-switch-link\"]/span/span"));

        if(newLang.equals("magyar")) {
            assertEquals("English", langButton.getText());
        }
        else if(newLang.equals("english")) {
            assertEquals("Magyar", langButton.getText());
        }
    }
}
