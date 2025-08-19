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
                chromeOptions.addArguments("--headless=new");


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
                options.addArguments("--headless=new");


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
// ة عشان    headless modeداتا جوجل خاص
//    public static WebDriver createDriverHeadless(String driverType) {
//        return switch (driverType.toLowerCase()) {
//            case "chrome" -> {
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless=new");
//                yield new ChromeDriver(chromeOptions);
//            }
//            case "firefox" -> {
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments("--headless");
//                yield new FirefoxDriver(firefoxOptions);
//            }
//            case "edge" -> {
//                EdgeOptions edgeOptions = new EdgeOptions();
//                edgeOptions.addArguments("--headless=new");
//                yield new EdgeDriver(edgeOptions);
//            }
//            default -> throw new IllegalArgumentException("Invalid driver type: " + driverType);
//        };
//    }
}
