package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementClickTests {
    @Test
    public void execute_nonExistent() {
        UiHost host = UiHost.getInstance();
        final String description = "Non-existent";
        final String locatorValue = "non-existent";
        final UiElement element = UiElement.getInstance(description, UiLocatorType.ID, locatorValue, host);
        UiElementGet nonExistentElement = element.getLocator();
        WebElementClick click = new WebElementClick(description, nonExistentElement);
        try {
            click.execute();
        } catch (Exception e) {
            Assert.fail("Failed attempt to click non-existent element without throwing an Exception");
        } finally {
            host.quit();
        }
    }

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
