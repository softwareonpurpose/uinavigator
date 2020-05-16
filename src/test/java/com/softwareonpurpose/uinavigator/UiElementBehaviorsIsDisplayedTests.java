package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsIsDisplayedTests extends TestClass {
    @Test
    public void testIsDisplayed() {
        UiHost host = UiHost.getInstance();
        final String attribute = "for";
        final String attributeValue = "name";
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttribute("Label", UiLocatorType.TAG, "label", attribute, attributeValue, host);
        MockView.directNav(host);
        boolean actual = behaviors.isDisplayed();
        host.quit();
        Assert.assertTrue(actual, "Failed to return 'true' for existing element");
    }
}
