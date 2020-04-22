package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebGetElementByLocator extends WebGetElementBehavior {
    private WebElement element;

    private WebGetElementByLocator(By locator) {
        super(locator);
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
