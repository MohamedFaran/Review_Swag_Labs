package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_loginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_loginTest {
    private static Set<Cookie> cookies;
    private String USERNAME = getJsonData("validLogin", "username");
    private String PASSWORD = getJsonData("validLogin", "password");

    @BeforeClass
    public void loginUsingCookies() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("driver is setup");// This is a placeholder for the setup method.
        getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("redirected to the login page");// You can initialize your test environment here.
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
        new P01_loginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton();
        cookies = Utility.getAllCookies(getDriver());
        quitDriver();

    }

    @BeforeMethod
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environment", "Browser"));
        LogsUtils.info("driver is setup");// This is a placeholder for the setup method.
        getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
        LogsUtils.info("redirected to the login page");// You can initialize your test environment here.
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait
        Utility.restoreSession(getDriver(), cookies);
        getDriver().get(DataUtils.getPropertyValue("environment", "HOME_URL"));

        getDriver().navigate().refresh();
        System.out.println("hh");
    }


    @Test
    public void validLoginTC() throws IOException {
        new P01_loginPage(getDriver()).enterUsername(DataUtils.getJsonData("validLogin", "username"))
                .enterPassword(DataUtils.getJsonData("validLogin", "password"))
                .clickOnLoginButton();
        Assert.assertTrue(new P01_loginPage(getDriver()).assertvalidlogintc(DataUtils.getJsonData("validLogin", "expectedUrl")));


//        new P01_loginPage(getDriver())
//                .enterUsername(getJsonData("validLogin", "username"))
//                .enterPassword(getJsonData("validLogin", "password"))
//                .clickOnLoginButton();
//        Assert.assertTrue(new P01_loginPage(getDriver()).asseretLoginTc(getPropertyValue("environment", "HOME_URL")));
//        ;
        // This is a placeholder for the test method.
        // You can implement your login test logic here.
    }

    @Test
    public void validLoginTCUsingCookies() throws IOException {

        Assert.assertTrue(new P01_loginPage(getDriver()).assertvalidlogintc(DataUtils.getJsonData("validLogin", "expectedUrl")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();

    }

    @AfterClass
    public void clearCookies() {
        cookies.clear();
        LogsUtils.info("Cookies cleared after test execution");
        // This is a placeholder for the cleanup method.
        // You can implement your cleanup logic here, such as closing the browser.
    }
    // This is a placeholder for the cleanup method.
    // You can implement your cleanup logic here, such as closing the browser.}

}
