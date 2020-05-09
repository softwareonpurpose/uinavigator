package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ElementState;
import com.softwareonpurpose.uinavigator.UiGetElement;
import org.openqa.selenium.WebElement;

public class WebElementIsActive extends ElementState {
    private final String attribute;
    private final String value;

    public WebElementIsActive(UiGetElement getElement, String attribute, String value) {
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
