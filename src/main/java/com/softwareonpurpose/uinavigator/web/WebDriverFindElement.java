package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverFindElement;
import com.softwareonpurpose.uinavigator.UiDriverFindElements;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WebDriverFindElement extends UiDriverFindElement {
    public WebDriverFindElement(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public WebElement execute(By locator) {
        List<Object> elements = UiDriverFindElements.getInstance(getDriver).execute(locator);
        if (elements.size() == 0) {
            final String message = String.format("WARNING: Unable to find any element %s", locator.toString());
            LoggerFactory.getLogger(this.getClass()).warn(message);
            return null;
        } else {
            return (WebElement) elements.get(0);
        }
    }
}
