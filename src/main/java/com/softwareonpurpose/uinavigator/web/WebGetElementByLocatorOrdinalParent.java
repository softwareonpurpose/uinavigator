package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorOrdinalParent extends WebGetElementBehavior {
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;

    private WebGetElementByLocatorOrdinalParent(By locator, Integer ordinal, WebGetElementBehavior getParent) {
        super(locator);
        this.ordinal = ordinal;
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetElementByLocatorOrdinalParent getInstance(
            String locatorType, String locatorValue, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetElementByLocatorOrdinalParent(WebUiLocator.getInstance(locatorType, locatorValue), ordinal, getParent);
    }

    @Override
    public WebElement execute() {
        List<WebElement> elements;
        if (getParent == null) {
            elements = WebUiHost.getInstance().findUiElements(locator);
        } else {
            elements = getParent.execute().findElements(locator);
        }
        return elements.size() >= ordinal ? elements.get(ordinal - 1) : null;
    }
}
