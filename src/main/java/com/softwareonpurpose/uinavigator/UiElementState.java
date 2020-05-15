package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementIsActive;
import com.softwareonpurpose.uinavigator.web.WebElementIsDisplayed;

public abstract class UiElementState {
    protected final UiElementGet getElement;
    protected final UiDriverGet getDriver;

    protected UiElementState(UiElementGet getElement, UiDriverGet getDriver) {
        this.getElement = getElement;
        this.getDriver = getDriver;
    }

    public static UiElementState getIsStateInstance(UiElementGet getElement, String attribute, String value, UiDriverGet getDriver) {
        return new WebElementIsActive(getElement, attribute, value, getDriver);
    }

    public static UiElementState getIsDisplayedInstance(UiElementGet getElement, UiDriverGet getDriver) {
        return new WebElementIsDisplayed(getElement, getDriver);
    }

    public abstract boolean execute();
}
