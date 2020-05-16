package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverSwitchTo;

public abstract class UiDriverSwitchTo {
    protected final UiDriverGet getDriver;

    public UiDriverSwitchTo(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverSwitchTo getInstance(UiDriverGet getDriver) {
        return new WebDriverSwitchTo(getDriver);
    }

    public abstract void execute(UiElement element);
}
