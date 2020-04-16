package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;

public class WebGetListByLocatorOnly implements WebGetListBehavior {
    private final By locator;
    @Deprecated
    private final String locatorType;
    @Deprecated
    private final String locatorValue;

    private WebGetListByLocatorOnly(String locatorType, String locatorValue) {
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        switch (locatorType) {
            case UiLocatorType.CLASS:
                locator = By.className(locatorValue);
                break;
            case UiLocatorType.NAME:
                locator = By.name(locatorValue);
                break;
            case UiLocatorType.TAG:
                locator = By.tagName(locatorValue);
                break;
            case UiLocatorType.ID:
            default:
                locator = By.id(locatorValue);
                break;
        }
    }

    public static WebGetListByLocatorOnly getInstance(String locatorType, String locatorValue) {
        return new WebGetListByLocatorOnly(locatorType, locatorValue);
    }

    @Override
    public Collection<WebUiElement> execute() {
        Collection<WebUiElement> elements = new ArrayList<>();
        WebGetByLocatorOnly getParent = WebGetByLocatorOnly.getInstance(UiLocatorType.TAG, "body");
        Collection<WebElement> webElements = getParent.execute().findElements(locator);
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal += 1) {
            String elementDescription = String.format("#%d", elementOrdinal);
            elements.add(WebUiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal, getParent));
        }
        return elements;
    }
}
