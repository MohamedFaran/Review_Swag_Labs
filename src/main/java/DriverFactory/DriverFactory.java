package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser) //Edge edge EDGE
    {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                // Setup ChromeOptions

                // 1. Disable pop-up notifications
                chromeOptions.addArguments("--disable-notifications");

                // 2. Optionally disable popups/infobars
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-infobars");

                // Start browser with options
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                // 1. Disable pop-up notifications
                options.addArguments("--disable-notifications");

                // 2. Optionally disable popups/infobars
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-infobars");

                // Start browser with options
                driverThreadLocal.set(new EdgeDriver(options));
        }

    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
