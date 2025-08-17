package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_cartPage {
    static float totalPrice = 0;
    private static List<WebElement> prices;
    private final WebDriver driver;
    private final By checkoutButton = By.id("checkout");

    private final By pricesOfSelectedProductsLoctor = By.xpath("//button[.=\"Remove\"]//preceding::div[@class='inventory_item_price']");

    public P03_cartPage(WebDriver driver) {
        this.driver = driver;
    }


    public String pricesOfSelectedProducts() {
        try {
            prices = driver.findElements(pricesOfSelectedProductsLoctor);
            for (int i = 1; i < prices.size(); i++) {
                LogsUtils.info("Price of selected product " + (i + 1) + ": " + prices.get(i).getText());
                By priceLocator = By.xpath("(//button[.='Remove']//preceding::div[@class='inventory_item_price'])[" + i + "]");
                Utility.getText(driver, pricesOfSelectedProductsLoctor);
                totalPrice += Float.parseFloat(Utility.getText(driver, priceLocator).replace("$", ""));
            }
            return String.valueOf(totalPrice);

        } catch (Exception e) {
            return "0";
        }
    }

    public boolean comparingTotalPrice() {
        return pricesOfSelectedProducts().equals(new P02_landingPage(driver).getTotalPriceLanding());
    }

    public P04_CheckoutPage clickOnCheckoutButton() {
        Utility.clickingOnElement(driver, checkoutButton);
        return new P04_CheckoutPage(driver);
    }
}