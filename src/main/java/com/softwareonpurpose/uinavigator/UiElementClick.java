package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementClick;

public abstract class UiElementClick {
    protected final String description;
    protected final UiElementGet getElement;

    protected UiElementClick(String description, UiElementGet getElement) {
        this.description = description;
        this.getElement = getElement;
    }

    public static UiElementClick getInstance(String description, UiElementGet getElement) {
        return new WebElementClick(description, getElement);
    }

    public abstract void execute();
}
