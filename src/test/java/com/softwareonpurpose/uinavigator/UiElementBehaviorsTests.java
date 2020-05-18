package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsTests extends TestClass {
    @Test
    public void testGetInstanceByLocator_tagSelect() {
        UiHost host = UiHost.getInstance();
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = UiElementBehaviors.getInstanceByLocator("Select", UiLocatorType.TAG, "select", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocator_idName() {
        UiHost host = UiHost.getInstance();
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = UiElementBehaviors.getInstanceByLocator("Name", UiLocatorType.ID, "name", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testSuppressLogging() {
        boolean expected = true;
        //noinspection ConstantConditions
        UiElementBehaviors.suppressLogging(expected);
        boolean actual = UiElementBehaviors.isLoggingSuppressed();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to set logging suppression");
    }
}
