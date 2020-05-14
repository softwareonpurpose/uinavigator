package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementIsActive;
import com.softwareonpurpose.uinavigator.web.WebElementIsDisplayed;

public abstract class ElementState {
    protected final UiElementGet getElement;

    protected ElementState(UiElementGet getElement) {
        this.getElement = getElement;
    }

    public static ElementState getIsActiveInstance(UiElementGet getElement, String attribute, String value) {
        return new WebElementIsActive(getElement, attribute, value);
    }

    public static ElementState getIsDisplayedInstance(UiElementGet getElement) {
        return new WebElementIsDisplayed(getElement);
    }

    public abstract boolean execute();
}
