package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttributeParent implements WebGetListBehavior {
    private final String attribute;
    private final String attributeValue;
    private final WebGetElementBehavior getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorAttributeParent(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, WebGetElementBehavior getParent) {
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetListByLocatorAttributeParent getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorAttributeParent(locatorType, locatorValue, attribute, attributeValue, getParent);
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
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                final String description = String.format("#%d", ordinal);
                elements.add(WebUiElement.getInstance(description, locatorType, locatorValue, this.attribute, this.attributeValue));
            }
        }
        return elements;
    }
}
