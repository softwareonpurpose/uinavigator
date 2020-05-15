package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiDriverGet;
import com.softwareonpurpose.uinavigator.UiElementGet;
import com.softwareonpurpose.uinavigator.UiElementState;
import org.openqa.selenium.WebElement;

public class WebElementIsActive extends UiElementState {
    private final String attribute;
    private final String value;

    public WebElementIsActive(UiElementGet getElement, String attribute, String value, UiDriverGet getDriver) {
        super(getElement, getDriver);
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
