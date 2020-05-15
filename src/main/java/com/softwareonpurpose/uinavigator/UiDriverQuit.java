package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverQuit;

public abstract class UiDriverQuit {
    protected final UiDriverGet getDriver;

    public UiDriverQuit(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverQuit getInstance(UiDriverGet getDriver) {
        return new WebDriverQuit(getDriver);
    }

    public abstract void execute();
}
