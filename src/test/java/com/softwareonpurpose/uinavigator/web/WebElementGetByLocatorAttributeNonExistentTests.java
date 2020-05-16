package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebElementGetByLocatorAttributeNonExistentTests extends TestClass {
    @Test
    public void testExecute_nonExistent() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        String description = "Non Existent";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "non-existent";
        final WebElementGetByLocatorAttribute getBehavior =
                WebElementGetByLocatorAttribute
                        .getInstance(description, UiLocatorType.ID, locatorValue, attribute, attributeValue, host);
        WebElement actual = getBehavior.execute();
        host.quit();
        Assert.assertNull(actual, "Failed to return null for nonexistent attribute");
    }
}
