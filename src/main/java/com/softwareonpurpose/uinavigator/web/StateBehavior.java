package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetElementBehavior;

public class StateBehavior {
    private final GetElementBehavior getBehavior;
    private final String attribute;
    private final String value;

    private StateBehavior(GetElementBehavior getBehavior, String attribute, String value) {
        this.getBehavior = getBehavior;
        this.attribute = attribute;
        this.value = value;
    }

    public static StateBehavior getInstance(GetElementBehavior getBehavior, String attribute, String value) {
        return new StateBehavior(getBehavior, attribute, value);
    }
}
