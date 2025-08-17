package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverviewPage {

    private final WebDriver driver;
    private final By subTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By totalLocator = By.xpath("//div[contains(@class,'summary_total_label')]");
    private final By finishButton = By.id("finish");

    public P05_OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    //10504.056504540..06


    public float itemTotal() {
        return Float.parseFloat(Utility.getText(driver, subTotal).replace("Item total: $", ""));
    }


    public Float tax() {
        return Float.parseFloat(Utility.getText(driver, tax).replace("Tax: $", ""));

    }

    public String totalPrice() {
        return String.valueOf(itemTotal() + tax());
    }

    public String total() {
        return Utility.getText(driver, totalLocator).replace("Total: $", "");
    }

    public boolean comparisonTotalPricewithTotal() {
        return totalPrice().equals(total());
    }

    public P06_FinishingOrderPage clickOnFinishButton() {
        Utility.clickingOnElement(driver, finishButton);
        return new P06_FinishingOrderPage(driver);
    }

}

