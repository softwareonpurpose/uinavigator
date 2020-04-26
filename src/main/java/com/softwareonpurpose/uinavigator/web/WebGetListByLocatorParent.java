package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorParent implements WebGetListBehavior {
    private final WebGetElementBehavior getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorParent(String locatorType, String locatorValue, WebGetElementBehavior getParent) {
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetListByLocatorParent getInstance(
            String locatorType, String locatorValue, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorParent(locatorType, locatorValue, getParent);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        List<WebElement> candidates;
        By locator = WebUiLocator.getInstance(locatorType, locatorValue);
        if (getParent == null) {
            candidates = WebUiHost.getInstance().findUiElements(locator);
        } else {
            candidates = getParent.execute().findElements(locator);
        }
        int ordinal = 0;
        //noinspection unused
        for (WebElement candidate : candidates) {
            ordinal += 1;
            elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locatorType, locatorValue));
        }
        return elements;
    }
}
