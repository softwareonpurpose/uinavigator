package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttributeOrdinalParent implements WebGetListBehavior {
    private final By locator;
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;

    private WebGetListByLocatorAttributeOrdinalParent(
            By locator, String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        this.locator = locator;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
        this.getParent = (new By.ByTagName("body").equals(locator)) ? null : getParent;
    }

    public static WebGetListByLocatorAttributeOrdinalParent getInstance(
            By locator, String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorAttributeOrdinalParent(locator, attribute, attributeValue, ordinal, getParent);
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
        Integer ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    elements.add(WebUiElement.getInstance(
                            String.format("#%d", ordinal), locator, this.attribute, this.attributeValue, ordinal));
                    return elements;
                }
            }
        }
        return elements;
    }
}
