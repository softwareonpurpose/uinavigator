package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;

public class WebGetListByLocator implements WebGetListBehavior {
    private final String locatorType;
    private final String locatorValue;

    private WebGetListByLocator(String locatorType, String locatorValue) {
        this.locatorValue = locatorValue;
        this.locatorType = locatorType;
    }

    public static WebGetListByLocator getInstance(String locatorType, String locatorValue) {
        return new WebGetListByLocator(locatorType, locatorValue);
    }

    @Override
    public Collection<WebUiElement> execute() {
        Collection<WebUiElement> elements = new ArrayList<>();
        Collection<WebElement> webElements;
        By locator = WebUiLocator.getInstance(locatorType, locatorValue);
        if (new By.ByTagName("body").equals(locator)) {
            webElements = WebUiHost.getInstance().findUiElements(locator);
        } else {
            WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "body");
            webElements = getParent.execute().findElements(locator);
        }
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal += 1) {
            String elementDescription = String.format("#%d", elementOrdinal);
            elements.add(WebUiElement.getInstance(elementDescription, locatorType, locatorValue, elementOrdinal));
        }
        return elements;
    }
}
