package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementIsActive;

public abstract class ElementState {
    protected final UiGetElement getElement;

    protected ElementState(UiGetElement getElement) {
        this.getElement = getElement;
    }

    public static ElementState getInstance(UiGetElement getElement, String attribute, String value) {
        return new WebElementIsActive(getElement, attribute, value);
    }

    public abstract boolean execute();
}
