package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementClick;
import com.softwareonpurpose.uinavigator.web.WebUiGetElement;

public abstract class ElementClick {
    protected final String description;
    protected final UiGetElement getElement;

    protected ElementClick(String description, UiGetElement getElement) {
        this.description = description;
        this.getElement = getElement;
    }

    public static ElementClick getInstance(String description, UiGetElement getElement) {
        return new WebElementClick(description, (WebUiGetElement) getElement);
    }

    public abstract void execute();
}
