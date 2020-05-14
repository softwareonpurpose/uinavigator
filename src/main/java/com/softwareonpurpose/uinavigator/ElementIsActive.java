package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiElementGet;
import com.softwareonpurpose.uinavigator.web.WebElementIsActive;

public abstract class ElementIsActive {
    public static ElementState getInstance(UiElementGet getElement, String attribute, String value) {
        return new WebElementIsActive((WebUiElementGet) getElement, attribute, value);
    }
}
