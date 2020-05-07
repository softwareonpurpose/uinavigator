package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebClickBehavior;
import com.softwareonpurpose.uinavigator.web.WebGetElementBehavior;

public abstract class ClickBehavior {
    protected final String description;
    protected final GetElementBehavior getElement;

    protected ClickBehavior(String description, GetElementBehavior getElement) {
        this.description = description;
        this.getElement = getElement;
    }

    public static ClickBehavior getInstance(String description, GetElementBehavior getElement) {
        return new WebClickBehavior(description, (WebGetElementBehavior) getElement);
    }

    public abstract void execute();
}
