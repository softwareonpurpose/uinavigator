package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorOrdinalParent implements WebGetListBehavior {
    private final By locator;
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;

    private WebGetListByLocatorOrdinalParent(By locator, Integer ordinal, WebGetElementBehavior getParent) {
        this.locator = locator;
        this.ordinal = ordinal;
        this.getParent = getParent;
    }

    public static WebGetListByLocatorOrdinalParent getInstance(
            By locator, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorOrdinalParent(locator, ordinal, getParent);
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
        if (candidates.size() >= ordinal) {
            elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locator, ordinal));
        }
        return elements;
    }
}
