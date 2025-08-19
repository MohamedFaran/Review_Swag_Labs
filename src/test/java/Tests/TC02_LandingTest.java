package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_loginPage;
import Pages.P02_landingPage;
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
public class TC02_LandingTest {

    // TODO: عشان اعمل run لاكتر من command لازم استخدم      mvn clean test -Dbrowser=chrome -Dtest=TC02_LandingTest   وليس -Dbrowser=chrom -Dtest=TC02_LandingTest clean test اخر واحد غلط
    //

    @BeforeMethod
    public void setup() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : DataUtils.getPropertyValue("environment", "Browser");
        createDriverHeadless(browser);
        LogsUtils.info("driver is setup");// This is a placeholder for the setup method.
        getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("redirected to the login page");// You can initialize your test environment here.
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
    }


    @Test
    public void assertNumberofselrctedItemswithNumberonCart() {
        new P01_loginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton().add_AllProductstoCart();
        Assert.assertTrue(new P02_landingPage(getDriver()).comparing_NumberOfSelectedProducts());
    }

    @Test
    public void assertRandomNumberofselrctedItemswithNumberonCart() {
        new P01_loginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton().addRandomProducts(3, 6);
        Assert.assertTrue(new P02_landingPage(getDriver()).comparing_NumberOfSelectedProducts());
    }

    @Test
    public void assertRedirectiontoCartPage() throws IOException {
        new P01_loginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton().addRandomProducts(3, 6)
                .clickOnCartIcon();
        Assert.assertTrue(new P02_landingPage(getDriver()).verifyAddtoCartPage(DataUtils.getPropertyValue("environment", "CART_URL")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}