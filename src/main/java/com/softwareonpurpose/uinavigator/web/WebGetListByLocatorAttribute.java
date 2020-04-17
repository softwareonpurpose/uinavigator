package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebGetListByLocatorAttribute implements WebGetListBehavior {
    private final By locator;
    private final String attribute;
    private final String attributeValue;

    private WebGetListByLocatorAttribute(By locator, String attribute, String attributeValue) {
        this.locator = locator;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }

    public static WebGetListByLocatorAttribute getInstance(By locator, String attribute, String attributeValue) {
        return new WebGetListByLocatorAttribute(locator, attribute, attributeValue);
    }

    @Override
    public List<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        int ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                elements.add(WebUiElement.getInstance(String.format("#%d", ordinal), locator, this.attribute, this.attributeValue));
            }
        }
        return elements;
    }
}
