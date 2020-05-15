package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementAttributeSet;

public abstract class UiElementAttributeSet {
    protected final UiElementGet getBehavior;
    protected final UiDriverGet getDriver;

    protected UiElementAttributeSet(UiElementGet getBehavior, UiDriverGet getDriver) {
        this.getBehavior = getBehavior;
        this.getDriver = getDriver;
    }

    public static UiElementAttributeSet getInstance(UiElementGet getElement, UiDriverGet getDriver) {
        return new WebElementAttributeSet(getElement, getDriver);
    }

    public abstract void execute(String attribute, String value);
}
