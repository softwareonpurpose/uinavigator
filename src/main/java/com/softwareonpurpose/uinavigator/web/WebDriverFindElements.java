package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverFindElements;
import com.softwareonpurpose.uinavigator.UiDriverGet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class WebDriverFindElements extends UiDriverFindElements {
    public WebDriverFindElements(UiDriverGet getDriver) {
        super(getDriver);
    }

    @Override
    public List<Object> execute(By locator) {
        List<Object> elements = new ArrayList<>();
        try {
            elements.addAll(((WebDriver) getDriver.execute()).findElements(locator));
        } catch (WebDriverException e) {
            LoggerFactory.getLogger(this.getClass()).warn(String.format("WARNING: Unable to find any element %s", locator.toString()));
        }
        return elements;
    }
}
