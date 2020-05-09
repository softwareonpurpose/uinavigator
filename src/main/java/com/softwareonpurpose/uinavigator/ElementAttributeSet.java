package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiGetElement;
import com.softwareonpurpose.uinavigator.web.WebElementAttributeSet;

public abstract class ElementAttributeSet {
    public static ElementAttributeSet getInstance(UiGetElement getElement) {
        return new WebElementAttributeSet((WebUiGetElement) getElement);
    }

    public abstract void execute(String attribute, String value);
}
