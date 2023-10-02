package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class GetByCssFromRoot extends GetWebElementBehavior {
    GetByCssFromRoot(By.ByCssSelector locator) {
        super(locator);
    }

    private static String getMessage(NoSuchElementException e) {
        String message = e.getMessage();
        int endIndex = message.indexOf("\n");
        return message.substring(0, endIndex);
    }

    public static GetByCssFromRoot getInstance(String locatorType, String locatorValue) {
        By.ByCssSelector locator = composeCss(locatorType, locatorValue);
        return new GetByCssFromRoot(locator);
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
