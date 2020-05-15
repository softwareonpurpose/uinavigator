package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverFindElement;
import org.openqa.selenium.By;

public abstract class UiDriverFindElement {
    protected final UiDriverGet getDriver;

    protected UiDriverFindElement(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverFindElement getInstance(UiDriverGet getDriver) {
        return new WebDriverFindElement(getDriver);
    }

    public abstract Object execute(By locator);
}
