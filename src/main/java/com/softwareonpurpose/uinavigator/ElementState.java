package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementIsActive;
import com.softwareonpurpose.uinavigator.web.WebElementIsDisplayed;

public abstract class ElementState {
    protected final UiGetElement getElement;

    protected ElementState(UiGetElement getElement) {
        this.getElement = getElement;
    }

    public static ElementState getIsActiveInstance(UiGetElement getElement, String attribute, String value) {
        return new WebElementIsActive(getElement, attribute, value);
    }

    public static ElementState getIsDisplayedInstance(UiGetElement getElement) {
        return new WebElementIsDisplayed(getElement);
    }

    public abstract boolean execute();
}
