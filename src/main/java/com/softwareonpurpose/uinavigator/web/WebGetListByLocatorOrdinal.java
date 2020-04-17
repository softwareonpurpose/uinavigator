package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorOrdinal implements WebGetListBehavior {
    private final By locator;
    private final Integer ordinal;

    private WebGetListByLocatorOrdinal(By locator, Integer ordinal) {
        this.locator = locator;
        this.ordinal = ordinal;
    }

    public static WebGetListByLocatorOrdinal getInstance(By locator, Integer ordinal) {
        return new WebGetListByLocatorOrdinal(locator, ordinal);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        if (candidates.size() >= ordinal) {
            elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locator, ordinal));
        }
        return elements;
    }
}
