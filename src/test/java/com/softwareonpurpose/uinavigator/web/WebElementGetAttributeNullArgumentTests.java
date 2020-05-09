package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementGetAttributeNullArgumentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nullArgument() {
        final String locatorValue = "empty-select-two";
        final String description = "Select";
        WebUiGetElement getBehavior =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        MockView.directNav();
        String actual = WebElementGetAttribute.getInstance(getBehavior).execute(null);
        Assert.assertNull(actual, "Failed to return null when requested attribute is null");
    }
}
