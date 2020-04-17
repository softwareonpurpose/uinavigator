package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttributeOrdinal implements WebGetListBehavior {
    private final By locator;
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;

    private WebGetListByLocatorAttributeOrdinal(By locator, String attribute, String attributeValue, Integer ordinal) {
        this.locator = locator;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
    }

    public static WebGetListByLocatorAttributeOrdinal getInstance(
            By locator, String attribute, String attributeValue, Integer ordinal) {
        return new WebGetListByLocatorAttributeOrdinal(locator, attribute, attributeValue, ordinal);
    }

    @Override
    public Collection<WebUiElement> execute() {
        List<WebUiElement> elements = new ArrayList<>();
        List<WebElement> candidates = WebUiHost.getInstance().findUiElements(locator);
        Integer ordinal = 0;
        for (WebElement candidate : candidates) {
            if (candidate.getAttribute(attribute).equals(attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    elements.add(WebUiElement.getInstance(
                            String.format("#%d", ordinal), locator, attribute, attributeValue, ordinal));
                    return elements;
                }
            }
        }
        return elements;
    }
}
