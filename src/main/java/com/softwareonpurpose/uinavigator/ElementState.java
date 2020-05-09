package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiGetElement;
import com.softwareonpurpose.uinavigator.web.WebElementIsActive;

public abstract class ElementState {
    public static ElementState getInstance(UiGetElement getElement, String attribute, String value) {
        return new WebElementIsActive((WebUiGetElement) getElement, attribute, value);
    }

    public abstract boolean execute();
}
