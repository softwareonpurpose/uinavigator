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

    private static String getMessage(NoSuchElementException e) {
        String message = e.getMessage();
        int endIndex = message.indexOf("\n");
        return message.substring(0, endIndex);
    }

    public static GetWebElementBehavior getInstance(String locatorType, String locatorValue) {
        return new GetByCssFromRoot(locatorType, locatorValue);
    }

    @Override
    public WebElement execute() {
        try {
            return UiNavigator.getInstance().getDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            LogManager.getLogger("").warn(getMessage(e));
            return null;
        }
    }
}
