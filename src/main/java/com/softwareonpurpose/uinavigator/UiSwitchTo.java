package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGet;
import com.softwareonpurpose.uinavigator.web.WebSwitchToFrame;
import com.softwareonpurpose.uinavigator.web.WebSwitchToView;

public abstract class UiSwitchTo {
    protected final UiDriverGet getDriver;

    protected UiSwitchTo(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiSwitchTo getFrameInstance(UiElementGet getBehavior, UiDriverGet getDriver) {
        return new WebSwitchToFrame((WebElementGet) getBehavior, getDriver);
    }

    public static UiSwitchTo getViewInstance(UiDriverGet getDriver) {
        return new WebSwitchToView(getDriver);
    }

    public abstract void execute();
}
