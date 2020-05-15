package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGet;
import com.softwareonpurpose.uinavigator.web.WebElementIsActive;

public abstract class UiElementIsActive {
    public static UiElementState getInstance(UiElementGet getElement, String attribute, String value, UiDriverGet getDriver) {
        return new WebElementIsActive((WebElementGet) getElement, attribute, value, getDriver);
    }
}
