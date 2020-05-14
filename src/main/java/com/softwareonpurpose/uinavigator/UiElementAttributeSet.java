package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementAttributeSet;

public abstract class UiElementAttributeSet {
    protected final UiElementGet getBehavior;

    protected UiElementAttributeSet(UiElementGet getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static UiElementAttributeSet getInstance(UiElementGet getElement) {
        return WebElementAttributeSet.getInstance(getElement);
    }

    public abstract void execute(String attribute, String value);
}
