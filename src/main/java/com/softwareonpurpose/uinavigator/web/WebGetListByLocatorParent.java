package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorParent implements WebGetListBehavior {
    private final By locator;
    private final WebGetElementBehavior getParent;

    private WebGetListByLocatorParent(By locator, WebGetElementBehavior getParent) {
        this.locator = locator;
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetListByLocatorParent getInstance(By locator, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorParent(locator, getParent);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        List<WebElement> candidates;
        if (getParent == null) {
            candidates = WebUiHost.getInstance().findUiElements(locator);
        } else {
            candidates = getParent.execute().findElements(locator);
        }
        int ordinal = 0;
        //noinspection unused
        for (WebElement candidate : candidates) {
            ordinal += 1;
            elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locator));
        }
        return elements;
    }
}
