package hu.masterfield.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    /* WebElement-ek */

    @FindBy(xpath = "//*[@id=\"sticky-bar-cookie-wrapper\"]/span/div/div/div[2]/form[1]/button")
    WebElement acceptButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/div/div[1]/div/input" )
    WebElement searchButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div/header/div/div[2]/div/form/button/span")
    WebElement searchIcon;

    @FindBy(xpath = "//*[@id=\"utility-header-language-switch-link\"]/span/span")
    WebElement langButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://bevasarlas.tesco.hu/groceries/");
    }


    public String getURL() {return driver.getCurrentUrl(); }

    public void acceptCookies() {
        acceptButton.click();
    }

    public void search(String product) {
        searchButton.click();
        searchButton.sendKeys(product);
        searchIcon.click();
        //return new SearchResultPage(driver);
    }


    public void setLangButton(String lang){
        if(langButton.getText().equals("Magyar") && lang.equals("magyar")) {
            langButton.click();
        }
        else if(langButton.getText().equals("English") && lang.equals("english")) {
            langButton.click();
        }
    }
}