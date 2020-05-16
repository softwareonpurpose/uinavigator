package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeNonExistentAttributeTests extends TestClass {
    @Test
    public void testExecute_nonexistentAttribute() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String description = "Non Existent";
        final String locatorValue = "name";
        final String attribute = "data-nonexistent";
        final String attributeValue = "non-existent";
        UiDriverBehaviors driverBehaviors = UiDriverBehaviors.getInstance();
        final WebElementGetByLocatorAttribute getBehavior = WebElementGetByLocatorAttribute.getInstance(
                description, UiLocatorType.ID, locatorValue, attribute, attributeValue, host);
        WebElement actual = getBehavior.execute();
        host.quit();
        Assert.assertNull(actual, "Failed to return null for nonexistent attribute");
    }
}
