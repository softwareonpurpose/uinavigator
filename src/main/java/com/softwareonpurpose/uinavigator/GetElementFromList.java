package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetElementFromList extends GetWebElementBehavior {
    private final By.ByCssSelector locator;

    public GetElementFromList(By.ByCssSelector locator) {
        this.locator = locator;
    }

    public static GetElementFromList getInstance(By.ByCssSelector locator) {
        return new GetElementFromList(locator);
    }

    @Override
    public WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (Exception e) {
            LogManager.getLogger("").warn(String.format("Element NOT FOUND using %s", locator));
        }
        return null;
    }
}
