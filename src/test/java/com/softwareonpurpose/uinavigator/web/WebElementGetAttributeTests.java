package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementGetAttributeTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        final String locatorValue = "empty-select-two";
        final String description = "Select";
        WebUiGetElement getBehavior =
                WebUiGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        String expected = "bogus";
        MockView.directNav();
        String actual = WebElementGetAttribute.getInstance(getBehavior).execute("data-test");
        Assert.assertEquals(actual, expected, "Failed to return attribute value");
    }
}
