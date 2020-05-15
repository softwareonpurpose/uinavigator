package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverLoad;

public abstract class UiDriverLoad {
    protected final UiDriverGet getDriver;

    protected UiDriverLoad(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverLoad getInstance(UiDriverGet getDriver) {
        return new WebDriverLoad(getDriver);
    }

    public abstract void execute(String address);
}
