package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttributeParent implements WebGetListBehavior {
    private final By locator;
    private final String attribute;
    private final String attributeValue;
    private final WebGetElementBehavior getParent;

    private WebGetListByLocatorAttributeParent(
            By locator, String attribute, String attributeValue, WebGetElementBehavior getParent) {
        this.locator = locator;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.getParent = (getParent == null && new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetListByLocatorAttributeParent getInstance(
            By locator, String attribute, String attributeValue, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorAttributeParent(locator, attribute, attributeValue, getParent);
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
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                final String description = String.format("#%d", ordinal);
                elements.add(WebUiElement.getInstance(description, locator, this.attribute, this.attributeValue));
            }
        }
        return elements;
    }
}
