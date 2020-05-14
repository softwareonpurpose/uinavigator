package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementAttributeSet;

public abstract class ElementAttributeSet {
    protected final UiElementGet getBehavior;

    protected ElementAttributeSet(UiElementGet getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static ElementAttributeSet getInstance(UiElementGet getElement) {
        return WebElementAttributeSet.getInstance(getElement);
    }

    public abstract void execute(String attribute, String value);
}
