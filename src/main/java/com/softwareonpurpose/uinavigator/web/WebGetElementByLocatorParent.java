package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebGetElementByLocatorParent implements WebGetElementBehavior {
    private final By locator;
    private final WebGetElementBehavior getParent;

    private WebGetElementByLocatorParent(By locator, WebGetElementBehavior getParent) {
        this.locator = locator;
        this.getParent = (getParent == null && new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetElementByLocatorParent getInstance(By locator, WebGetElementBehavior getParent) {
        return new WebGetElementByLocatorParent(locator, getParent);
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
