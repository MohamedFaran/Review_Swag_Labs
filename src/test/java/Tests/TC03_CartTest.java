package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_loginPage;
import Pages.P03_cartPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})

public class TC03_CartTest {


    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("driver is setup");// This is a placeholder for the setup method.
        getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("redirected to the login page");// You can initialize your test environment here.
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
    }


    @Test
    public void assertSelectedProductsWithPricesonCartPage() throws IOException {
        new P01_loginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton().addRandomProducts(3, 6)
                .clickOnCartIcon()
                .clickOnCheckoutButton();
        Assert.assertTrue(new P03_cartPage(getDriver()).comparingTotalPrice());
    }


    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
