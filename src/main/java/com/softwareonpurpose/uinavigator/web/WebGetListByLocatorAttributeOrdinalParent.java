package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebGetListByLocatorAttributeOrdinalParent implements WebGetListBehavior {
    private final String attribute;
    private final String attributeValue;
    private final Integer ordinal;
    private final WebGetElementBehavior getParent;
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocatorAttributeOrdinalParent(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        this.attribute = attribute;
        this.attributeValue = attributeValue;
        this.ordinal = ordinal;
        this.getParent = (UiLocatorType.TAG.equals(locatorType) && "body".equals(locatorValue)) ? null : getParent;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public static WebGetListByLocatorAttributeOrdinalParent getInstance(
            String locatorType, String locatorValue,
            String attribute, String attributeValue, Integer ordinal, WebGetElementBehavior getParent) {
        return new WebGetListByLocatorAttributeOrdinalParent(
                locatorType, locatorValue, attribute, attributeValue, ordinal, getParent);
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
        Integer ordinal = 0;
        for (WebElement candidate : candidates) {
            final String attributeValue = candidate.getAttribute(this.attribute);
            if (attributeValue != null && attributeValue.equals(this.attributeValue)) {
                ordinal += 1;
                if (ordinal.equals(this.ordinal)) {
                    elements.add(WebUiElement.getInstance(
                            String.format("#%d", ordinal), locatorType, locatorValue,
                            this.attribute, this.attributeValue, ordinal));
                    return elements;
                }
            }
        }
        return elements;
    }
}
