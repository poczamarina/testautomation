package hu.masterfield.steps;

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

    @Given("search bar is on the page")
    public void searchBarIsOnThePage() {
        ///html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/input[1]
        WebElement searchButton = wait.until(driver -> driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/input[1]")));
        //olyan oldalon állok ahol van keresősáv
    }

    @When("search a {string} which exist")
    public void searchAWhichExist(String product) throws InterruptedException {

        WebElement searchButton = wait.until(driver -> driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/div/div[1]/div/input")));
        searchButton.click();
        searchButton.sendKeys(product);
        WebElement searchIcon = wait.until(driver -> driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/button/span")));
        searchIcon.click();
        Thread.sleep(10000);

        /*
        HomePage homePage =new HomePage(driver);
        assertEquals("https://tesco/hu-hu", homePage.getURL());
        SearchResultPage searchResultPage = homePage.search(product);
        searchResultPage.validate();
        assertEquals("https://bevasarlas.tesco.hu/groceries/en-GB/search", searchResultPage.getURL());

         */
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
