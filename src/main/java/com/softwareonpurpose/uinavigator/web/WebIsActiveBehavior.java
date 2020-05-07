package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiStateBehavior;

public class WebIsActiveBehavior extends UiStateBehavior {
    private final WebGetElementBehavior getElement;
    private final String attribute;
    private final String value;

    public WebIsActiveBehavior(WebGetElementBehavior getElement, String attribute, String value) {
        this.getElement = getElement;
        this.attribute = attribute;
        this.value = value;
    }

    @Override
    public boolean execute() {
        if (attribute == null || value == null) {
            return false;
        }
        return value.equals(getElement.execute().getAttribute(attribute));
    }
}
