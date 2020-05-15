package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverGetAddress;

public abstract class UiDriverGetAddress {
    protected final UiDriverGet getDriver;

    protected UiDriverGetAddress(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverGetAddress getInstance(UiDriverGet getDriver) {
        return new WebDriverGetAddress(getDriver);
    }

    public abstract String execute();
}
