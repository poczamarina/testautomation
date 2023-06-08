package hu.masterfield.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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

public class SearchBarSteps {

    protected static WebDriver driver;

    protected static WebDriverWait wait;

    @Before // cucumber annotáció
    public static void setup() throws IOException {
        WebDriverManager.chromedriver().setup();

        // loading arguments, properties
        Properties props = new Properties(); // java.util
        InputStream is = ChangeLangSteps.class.getResourceAsStream("/browser.properties");
        props.load(is);

        // set chrome options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(props.getProperty("chrome.arguments"));
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.setHeadless(true);

        // init driver
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().setSize(new Dimension(900, 900)); // ...selenium.Dimension
    }

    @After
    public static void cleanup() {
        driver.quit();
    }

    @Given("search bar is on the page")
    public void searchBarIsOnThePage() {
        ///html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/input[1]
        WebElement searchButton = wait.until(driver -> driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/input[1]")));
        //olyan oldalon állok ahol van keresősáv
    }

    @When("search a product which exist")
    public void searchAProductWhichExist(String product) {
        WebElement searchButton = wait.until(driver -> driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/input[1]")));
        searchButton.click();
        searchButton.sendKeys(product);
        WebElement searchIcon = wait.until(driver -> driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/input[1]")));
        searchIcon.click();
    }

    @Then("it shows the results")
    public void itShowsTheResults() {
    }

    @When("search a product which is not exist")
    public void searchAProductWhichIsNotExist() {
    }

    @Then("it shows not found message")
    public void itShowsNotFoundMessage() {
    }
}
