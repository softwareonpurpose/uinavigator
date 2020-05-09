package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementClick;

public abstract class ElementClick {
    protected final String description;
    protected final UiGetElement getElement;

    protected ElementClick(String description, UiGetElement getElement) {
        this.description = description;
        this.getElement = getElement;
    }

    public static ElementClick getInstance(String description, UiGetElement getElement) {
        return new WebElementClick(description, getElement);
    }

    public abstract void execute();
}
