package hu.masterfield.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchBarSteps {

    protected static WebDriver driver;

    protected static WebDriverWait wait;

    @Given("search bar is on the page")
    public void searchBarIsOnThePage() {
    }

    @When("search a product which exist")
    public void searchAProductWhichExist() {
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
