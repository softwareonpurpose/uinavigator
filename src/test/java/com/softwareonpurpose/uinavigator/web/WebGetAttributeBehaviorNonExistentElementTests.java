package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetAttributeBehaviorNonExistentElementTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonExistentElement() {
        final String description = "Bogus";
        final String locatorValue = "bogus";
        WebGetElementBehavior getBehavior =
                WebGetElementByLocator.getInstance(description, UiLocatorType.ID, locatorValue);
        MockView.directNav();
        String actual = WebGetAttributeBehavior.getInstance(getBehavior).execute(null);
        Assert.assertNull(actual, "Failed to return null when element non-existent");
    }
}
