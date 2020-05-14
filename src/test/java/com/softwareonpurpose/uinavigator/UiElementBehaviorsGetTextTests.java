package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsGetTextTests {
    @AfterMethod
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testGetText() {
        UiElementBehaviors behaviors = UiElementBehaviors.getInstanceByLocator("Page", UiLocatorType.TAG, "body");
        String expected = "";
        String actual = behaviors.getText();
        WebHost.quitInstance();
        String message = "Failed to return expected text";
        Assert.assertEquals(actual, expected, message);
    }
}
