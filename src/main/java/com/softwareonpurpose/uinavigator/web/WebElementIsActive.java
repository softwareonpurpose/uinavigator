package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElementState;
import com.softwareonpurpose.uinavigator.UiElementGet;
import org.openqa.selenium.WebElement;

public class WebElementIsActive extends UiElementState {
    private final String attribute;
    private final String value;

    public WebElementIsActive(UiElementGet getElement, String attribute, String value) {
        super(getElement);
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
