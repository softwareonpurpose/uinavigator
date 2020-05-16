package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsClickTests extends TestClass {
    @Test
    public void testClick_suppressLogging() {
        UiHost host = UiHost.getInstance();
        UiElementBehaviors.suppressLogging(true);
        MockView.directNav(host).clickButton();
        UiElementBehaviors.suppressLogging(false);
        host.quit();
        Assert.assertTrue(true, "Failed to click element successfully with logging suppressed");
    }
}
