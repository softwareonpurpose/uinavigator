package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiStateBehavior;
import org.openqa.selenium.WebElement;

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
        return value.equals(((WebElement) getElement.execute()).getAttribute(attribute));
    }
}
