package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementAttributeSet;

public abstract class ElementAttributeSet {
    protected final UiGetElement getBehavior;

    protected ElementAttributeSet(UiGetElement getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static ElementAttributeSet getInstance(UiGetElement getElement) {
        return WebElementAttributeSet.getInstance(getElement);
    }

    public abstract void execute(String attribute, String value);
}
