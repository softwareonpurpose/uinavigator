package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementAttributeSet;

public abstract class UiElementAttributeSet {
    protected final UiHost host;
    protected final UiElementGet getBehavior;

    protected UiElementAttributeSet(UiElementGet getBehavior, UiHost host) {
        this.getBehavior = getBehavior;
        this.host = host;
    }

    public static UiElementAttributeSet getInstance(UiElementGet getElement, UiHost host) {
        return new WebElementAttributeSet(getElement, host);
    }

    public abstract void execute(String attribute, String value);
}
