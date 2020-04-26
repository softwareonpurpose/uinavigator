package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ByIdOrName;

public class WebUiLocator {
    public static By getInstance(String locatorType, String locatorValue) {
        if (locatorType == null || locatorValue == null) {
            return null;
        }
        By locator;
        switch (locatorType) {
            case UiLocatorType.CLASS:
                locator = new By.ByClassName(locatorValue);
                break;
            case UiLocatorType.TAG:
                locator = new By.ByTagName(locatorValue);
                break;
            case UiLocatorType.ID:
            case UiLocatorType.NAME:
            default:
                locator = new ByIdOrName(locatorValue);
        }
        return locator;
    }
}
