package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementIsActive;

public abstract class UiElementIsActive {
    public static UiElementState getInstance(UiElementGet getElement, String attribute, String value) {
        return new WebElementIsActive(getElement, attribute, value);
    }
}
