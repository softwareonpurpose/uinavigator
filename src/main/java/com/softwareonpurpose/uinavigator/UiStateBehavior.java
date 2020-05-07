package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebGetElementBehavior;
import com.softwareonpurpose.uinavigator.web.WebIsActiveBehavior;

public abstract class UiStateBehavior {
    public static UiStateBehavior getInstance(GetElementBehavior getElement, String attribute, String value) {
        return new WebIsActiveBehavior((WebGetElementBehavior) getElement, attribute, value);
    }

    public abstract boolean execute();
}
