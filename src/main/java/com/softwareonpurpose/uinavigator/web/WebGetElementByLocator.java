package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebGetElementByLocator implements WebGetElementBehavior {
    private final By locator;
    private WebElement element;

    private WebGetElementByLocator(By locator) {
        this.locator = locator;
    }

    public static WebGetElementByLocator getInstance(By locator) {
        return new WebGetElementByLocator(locator);
    }

    @Override
    public WebElement execute() {
        if (element == null) {
            element = WebUiHost.getInstance().findUiElement(locator);
        }
        return element;
    }
}
