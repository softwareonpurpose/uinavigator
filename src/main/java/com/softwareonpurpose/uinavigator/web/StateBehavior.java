package com.softwareonpurpose.uinavigator.web;

public class StateBehavior {
    private final WebGetElementBehavior getBehavior;
    private final String attribute;
    private final String value;

    private StateBehavior(WebGetElementBehavior getBehavior, String attribute, String value) {
        this.getBehavior = getBehavior;
        this.attribute = attribute;
        this.value = value;
    }

    public static StateBehavior getInstance(WebGetElementBehavior getBehavior, String attribute, String value) {
        return new StateBehavior(getBehavior, attribute, value);
    }

    public boolean execute() {
        return getBehavior.execute().getAttribute(attribute).contains(value);
    }
}
