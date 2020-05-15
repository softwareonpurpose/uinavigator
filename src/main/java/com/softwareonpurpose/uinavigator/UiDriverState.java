package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverState;

public abstract class UiDriverState {
    protected final UiDriverGet getDriver;

    protected UiDriverState(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverState getInstance(UiDriverGet getDriver) {
        return new WebDriverState(getDriver);
    }

    public abstract String execute(String[] identifiers);
}
