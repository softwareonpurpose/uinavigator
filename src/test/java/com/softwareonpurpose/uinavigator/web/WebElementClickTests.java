package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.annotations.Test;

@Test
public class WebElementClickTests {
    @Test
    public void execute_loggingSuppressed() {
        UiElementBehaviors.suppressLogging(true);
        UiHost host = UiHost.getInstance();
        final String description = "Button";
        final String locatorValue = "button-1";
        UiElementGet nonExistentElement =
                UiElement.getInstance(description, UiLocatorType.ID, locatorValue, host).getLocator();
        new WebElementClick(description, nonExistentElement).execute();
        UiElementBehaviors.suppressLogging(false);
    }
}
