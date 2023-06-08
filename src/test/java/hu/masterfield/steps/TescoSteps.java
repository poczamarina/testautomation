package hu.masterfield.steps;

import hu.masterfield.pages.BasePage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.SearchResultPage;
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
    HomePage hp =new HomePage(driver);
        hp.openPage();
    }

    @And("accept cookies")
    public void acceptCookies() throws InterruptedException {
        HomePage hp =new HomePage(driver);
        hp.acceptCookies();
    }


    @Given("language is set to {string}")
    public void languageIsSetTo(String lang ) throws InterruptedException {
        HomePage hp =new HomePage(driver);
        hp.setLangButton(lang);
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


    @Given("search bar is on the page")
    public void searchBarIsOnThePage() {
        WebElement searchButton = wait.until(driver -> driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/input[1]")));
    }

    @And("the language is {string}")
    public void theLanguageIs(String lang) {
        HomePage hp =new HomePage(driver);
        hp.setLangButton(lang);
    }

    @When("search a {string} which exist")
    public void searchAWhichExist(String product) throws InterruptedException {
        HomePage hp =new HomePage(driver);
        hp.search(product);
    }


    @Then("it shows the results which contains {string}")
    public void itShowsTheResultsWhichContains(String product) throws InterruptedException {
        HomePage hp =new HomePage(driver);
        assertEquals("https://bevasarlas.tesco.hu/groceries/hu-HU/search?query="+product, hp.getURL());
    }


    @When("search a {string} which is not exist")
    public void searchAWhichIsNotExist(String product) {
        HomePage hp =new HomePage(driver);
        hp.search(product);
    }

    @Then("it shows not found the {string} message")
    public void itShowsNotFoundTheMessage(String product) throws InterruptedException {
        HomePage hp =new HomePage(driver);
        assertEquals("https://bevasarlas.tesco.hu/groceries/hu-HU/search?query="+product, hp.getURL());
    }



}
