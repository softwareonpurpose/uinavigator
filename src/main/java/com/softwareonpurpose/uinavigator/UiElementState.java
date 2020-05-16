package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementIsActive;

public abstract class UiElementState {
    protected final UiElementGet getElement;

    protected UiElementState(UiElementGet getElement) {
        this.getElement = getElement;
    }

    public static UiElementState getIsStateInstance(UiElementGet getElement, String attribute, String value) {
        return new WebElementIsActive(getElement, attribute, value);
    }

    public abstract boolean execute();
}
