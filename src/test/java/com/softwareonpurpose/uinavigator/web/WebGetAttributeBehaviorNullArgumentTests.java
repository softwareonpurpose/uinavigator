package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetAttributeBehaviorNullArgumentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nullArgument() {
        final String locatorValue = "empty-select-two";
        WebGetElementBehavior getBehavior = WebGetElementByLocator.getInstance(UiLocatorType.ID, locatorValue);
        MockView.directNav();
        String actual = WebGetAttributeBehavior.getInstance(getBehavior).execute(null);
        Assert.assertNull(actual, "Failed to return null when requested attribute is null");
    }
}
