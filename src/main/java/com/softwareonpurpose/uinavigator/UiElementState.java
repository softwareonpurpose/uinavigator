package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementIsActive;
import com.softwareonpurpose.uinavigator.web.WebElementIsDisplayed;

public abstract class UiElementState {
    protected final UiElementGet getElement;

    protected UiElementState(UiElementGet getElement) {
        this.getElement = getElement;
    }

    public static UiElementState getIsActiveInstance(UiElementGet getElement, String attribute, String value) {
        return new WebElementIsActive(getElement, attribute, value);
    }

    public static UiElementState getIsDisplayedInstance(UiElementGet getElement) {
        return new WebElementIsDisplayed(getElement);
    }

    public abstract boolean execute();
}
