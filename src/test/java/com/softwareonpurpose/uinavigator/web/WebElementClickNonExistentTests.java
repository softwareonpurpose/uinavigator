package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiElementGet;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementClickNonExistentTests {
    @Test(groups = "debug")
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
}
