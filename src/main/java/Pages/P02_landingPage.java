package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_landingPage {
    static float totalPrice = 0;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;
    private static List<WebElement> prices;
    private final WebDriver driver;
    private final By addToCartButtonForAllProducts = By.xpath("//button[@class]");
    private final By numberOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By numberOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By pricesOfSelectedProductsLoctor = By.xpath("//button[.=\"Remove\"]//preceding::div[@class='inventory_item_price']");

    public P02_landingPage(WebDriver driver) {
        this.driver = driver;
    }

    public P02_landingPage add_AllProductstoCart() {
        try {
            allProducts = driver.findElements(addToCartButtonForAllProducts);
            LogsUtils.info("Number of all products " + allProducts.size());
            for (int i = 1; i <= allProducts.size(); i++) {
                By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + i + "]");
                driver.findElement(addToCartButtonForAllProducts).click();
                return this;
            }

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return this;
        }
        return this;

    }

    public String getNumberOfProductsOnCartIcon() {
        return Utility.getText(driver, numberOfProductsOnCartIcon);
    }

    public String getNumberOfSelectedProducts() {
        selectedProducts = driver.findElements(numberOfSelectedProducts);
        return String.valueOf(selectedProducts.size());
    }

    public boolean comparing_NumberOfSelectedProducts() {
        return getNumberOfSelectedProducts().equals(getNumberOfProductsOnCartIcon());
    }

    public P02_landingPage addRandomProducts(int neededNumberofProduct, int totalNumberofProduct) {

        Set<Integer> uniqueRandomproducts = Utility.generateUniqueNumber(neededNumberofProduct, totalNumberofProduct);
        for (int random : uniqueRandomproducts) {
            LogsUtils.info("Random number: " + random);
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + random + "]");
            Utility.clickingOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;

    }

    public P03_cartPage clickOnCartIcon() {
        Utility.clickingOnElement(driver, By.className("shopping_cart_link"));
        return new P03_cartPage(driver);
    }

    public boolean verifyAddtoCartPage(String expectedValue) {
        try {
            return driver.getCurrentUrl().equals(expectedValue);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }
    }

    public String pricesOfSelectedProductsLanding() {
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

    public String getTotalPriceLanding() {
        return pricesOfSelectedProductsLanding();
    }
}
