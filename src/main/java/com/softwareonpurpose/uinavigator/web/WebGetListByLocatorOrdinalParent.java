package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorOrdinalParent implements WebGetListBehavior {
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorOrdinalParent(String locatorType, String locatorValue, Integer ordinal, WebGetElementBehavior getParent) {
        this.ordinal = ordinal;
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetListByLocatorOrdinalParent getInstance(
            String locatorType, String locatorValue, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorOrdinalParent(locatorType, locatorValue, ordinal, getParent);
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
        if (candidates.size() >= ordinal) {
            elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locatorType, locatorValue, ordinal));
        }
        return elements;
    }
}
