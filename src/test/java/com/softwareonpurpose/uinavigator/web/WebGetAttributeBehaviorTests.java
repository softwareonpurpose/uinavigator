package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetAttributeBehaviorTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        final String locatorValue = "empty-select-two";
        WebGetElementBehavior getBehavior = WebGetElementByLocator.getInstance(UiLocatorType.ID, locatorValue);
        String expected = "bogus";
        MockView.directNav();
        String actual = WebGetAttributeBehavior.getInstance(getBehavior).execute("data-test");
        Assert.assertEquals(actual, expected, "Failed to return attribute value");
    }
}
