package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.ElementBehaviors;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebElementBehaviorsGetTextTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetText() {
        ElementBehaviors behaviors = WebElementBehaviors.getInstanceByLocator("Page", UiLocatorType.TAG, "body");
        String expected = "";
        String actual = behaviors.getText();
        WebUiHost.quitInstance();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }
}
