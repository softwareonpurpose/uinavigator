package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverWaitUntilVisible;

public abstract class UiDriverWaitUntilVisible {
    protected final UiDriverGet getDriver;

    protected UiDriverWaitUntilVisible(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverWaitUntilVisible getInstance(UiDriverGet getDriver) {
        return new WebDriverWaitUntilVisible(getDriver);
    }

    public abstract boolean execute(Object element);
}
