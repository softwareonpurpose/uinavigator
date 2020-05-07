package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebGetElementBehavior;
import com.softwareonpurpose.uinavigator.web.WebIsActiveBehavior;

public abstract class IsActiveBehavior {
    public static UiStateBehavior getInstance(GetElementBehavior getElement, String attribute, String value) {
        return new WebIsActiveBehavior((WebGetElementBehavior) getElement, attribute, value);
    }
}
