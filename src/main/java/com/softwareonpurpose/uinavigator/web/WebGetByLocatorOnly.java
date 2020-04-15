package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebGetByLocatorOnly implements WebGetElementBehavior{
    private final By locator;

    public WebGetByLocatorOnly(String locatorType, String locatorValue) {
        switch (locatorType) {
            case UiLocatorType.CLASS:
                locator = By.className(locatorValue);
                break;
            case UiLocatorType.ID:
                locator = By.id(locatorValue);
                break;
            case UiLocatorType.NAME:
                locator = By.name(locatorValue);
                break;
            case UiLocatorType.TAG:
                locator = By.tagName(locatorValue);
                break;
            default:
                locator = null;
        }
    }

    public static WebGetByLocatorOnly getInstance(String locatorType, String locatorValue) {
        return new WebGetByLocatorOnly(locatorType, locatorValue);
    }

    @Override
    public WebElement execute() {
        return WebUiHost.getInstance().findUiElement(locator);
    }
}
