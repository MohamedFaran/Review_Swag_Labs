package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_loginPage {
    private final WebDriver driver;
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");

    public P01_loginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_loginPage enterPassword(String passwordText) {
        Utility.sendData(driver, password, passwordText);
        return this;
    }

    public P01_loginPage enterUsername(String usernameText) {
        Utility.sendData(driver, username, usernameText);
        return this;
    }

    public P02_landingPage clickOnLoginButton() {
        Utility.clickingOnElement(driver, loginButton);
        return new P02_landingPage(driver);
    }

    //    public boolean asseretLoginTc(String expectedValue) {
//        return driver.getCurrentUrl().equals(expectedValue);
//    }
    public boolean assertvalidlogintc(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }


}