package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class GetWebElementBehavior {
    protected final By.ByCssSelector locator;

    protected GetWebElementBehavior(By.ByCssSelector locator) {
        this.locator = locator;
    }

    protected static By.ByCssSelector composeCss(String locatorType, String locatorValue) {
        String css = String.format("%s%s", locatorType, locatorValue);
        return new By.ByCssSelector(css);
    }

    abstract WebElement execute();
}
