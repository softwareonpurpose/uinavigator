package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebGetElementByLocatorOrdinal implements WebGetElementBehavior {
    private final By locator;
    private final Integer ordinal;

    private WebGetElementByLocatorOrdinal(By locator, Integer ordinal) {
        this.locator = locator;
        this.ordinal = ordinal;
    }

    public static WebGetElementByLocatorOrdinal getInstance(By locator, Integer ordinal) {
        return new WebGetElementByLocatorOrdinal(locator, ordinal);
    }

    @Override
    public WebElement execute() {
        return WebUiHost.getInstance().findUiElements(locator).get(ordinal - 1);
    }
}
