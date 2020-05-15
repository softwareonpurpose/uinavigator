package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsClickTests extends TestClass {
    @AfterMethod
    public void terminate() {
        host.quit();
    }

    @Test
    public void testClick_suppressLogging() {
        host = UiHost.getInstance();
        UiElementBehaviors.suppressLogging(true);
        MockView.directNav(host).clickButton();
        UiElementBehaviors.suppressLogging(false);
        Assert.assertTrue(true, "Failed to click element successfully with logging suppressed");
    }
}
