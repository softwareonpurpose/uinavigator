package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorOrdinal extends WebGetElementBehavior {
    private final Integer ordinal;

    private WebGetElementByLocatorOrdinal(By locator, Integer ordinal) {
        super(locator);
        this.ordinal = ordinal;
    }

    @Deprecated
    public static WebGetElementByLocatorOrdinal getInstance(By locator, Integer ordinal) {
        return new WebGetElementByLocatorOrdinal(locator, ordinal);
    }

    public static WebGetElementBehavior getInstance(String locatorType, String locatorValue, Integer ordinal) {
        return new WebGetElementByLocatorOrdinal(WebUiLocator.getInstance(locatorType, locatorValue), ordinal);
    }

    @Override
    public WebElement execute() {
        final List<WebElement> elements = WebUiHost.getInstance().findUiElements(locator);
        return elements.size() >= ordinal ? elements.get(ordinal - 1) : null;
    }
}
