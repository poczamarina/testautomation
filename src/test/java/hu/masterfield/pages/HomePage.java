package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    /* WebElement-ek */

    @FindBy(xpath = "//*[@id=\"sticky-bar-cookie-wrapper\"]/span/div/div/div[2]/form[1]/button")
    WebElement acceptButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://bevasarlas.tesco.hu/groceries/");
    }

    public void acceptCookies() {
        acceptButton.click();
    }

    public SearchResultPage search(String word) {
        /*
         * itt megtortenik a kereses
         * */
        return new SearchResultPage(driver);
    }

}
