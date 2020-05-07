package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebGetElementBehavior;
import com.softwareonpurpose.uinavigator.web.WebSetAttributeBehavior;

public abstract class SetAttributeBehavior {
    public static SetAttributeBehavior getInstance(GetElementBehavior getElement) {
        return new WebSetAttributeBehavior((WebGetElementBehavior) getElement);
    }

    public abstract void execute(String attribute, String value);
}
