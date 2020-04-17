package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;

public class WebGetListByLocator implements WebGetListBehavior {
    private final By locator;

    private WebGetListByLocator(By locator) {
        this.locator = locator;
    }

    public static WebGetListByLocator getInstance(
            By locator) {
        return new WebGetListByLocator(locator);
    }

    @Override
    public Collection<WebUiElement> execute() {
        Collection<WebUiElement> elements = new ArrayList<>();
        Collection<WebElement> webElements;
        if (new By.ByTagName("body").equals(locator)) {
            webElements = WebUiHost.getInstance().findUiElements(locator);
        } else {
            WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(new By.ByTagName("body"));
            webElements = getParent.execute().findElements(locator);
        }
        for (int elementOrdinal = 1; elementOrdinal <= webElements.size(); elementOrdinal += 1) {
            String elementDescription = String.format("#%d", elementOrdinal);
            elements.add(WebUiElement.getInstance(elementDescription, locator, elementOrdinal));
        }
        return elements;
    }
}
