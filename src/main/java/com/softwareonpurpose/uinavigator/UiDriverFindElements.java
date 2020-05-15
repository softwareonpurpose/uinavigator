package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverFindElements;
import org.openqa.selenium.By;

import java.util.List;

public abstract class UiDriverFindElements {
    protected final UiDriverGet getDriver;

    protected UiDriverFindElements(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverFindElements getInstance(UiDriverGet getDriver) {
        return new WebDriverFindElements(getDriver);
    }

    public abstract List<Object> execute(By locator);
}
