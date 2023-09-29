package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByCssFromRoot extends GetWebElementBehavior {
    private final By.ByCssSelector locator;

    GetByCssFromRoot(String locatorType, String locatorValue) {
        locator = new By.ByCssSelector(String.format("%s%s", locatorType, locatorValue));
    }

    @Override
    public WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(e.getMessage());
            return null;
        }
    }
}
