package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorParent extends WebGetElementBehavior {
    private final WebGetElementBehavior getParent;

    private WebGetElementByLocatorParent(By locator, WebGetElementBehavior getParent) {
        super(locator);
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    @Deprecated
    public static WebGetElementByLocatorParent getInstance(By locator, WebGetElementBehavior getParent) {
        return new WebGetElementByLocatorParent(locator, getParent);
    }

    public static WebGetElementByLocatorParent getInstance(
            String locatorType, String locatorValue, WebGetElementBehavior getParent) {
        return new WebGetElementByLocatorParent(WebUiLocator.getInstance(locatorType, locatorValue), getParent);
    }

    @Override
    public WebElement execute() {
        List<WebElement> elements;
        if (getParent == null) {
            elements = WebUiHost.getInstance().findUiElements(locator);
        } else {
            elements = getParent.execute().findElements(locator);
        }
        return elements.get(0);
    }
}
