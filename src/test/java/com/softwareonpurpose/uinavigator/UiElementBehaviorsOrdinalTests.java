package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGetByLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementBehaviorsOrdinalTests extends TestClass {
    @Test
    public void testGetInstanceByLocatorAttributeOrdinal() {
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        UiHost host = UiHost.getInstance();
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinal("Select", UiLocatorType.TAG, "select", attribute, attributeValue, ordinal, host);
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        UiHost host = UiHost.getInstance();
        final String description = "Form";
        final String locatorValue = "form";
        WebElementGetByLocator getParent =
                WebElementGetByLocator.getInstance(description, UiLocatorType.TAG, locatorValue, host);
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final UiElementBehaviors behaviors =
                UiElementBehaviors.getInstanceByLocatorAttributeOrdinalParent("Select",
                        UiLocatorType.TAG, "select", attribute, attributeValue,
                        ordinal, getParent, host);
        Class<UiElementBehaviors> expected = UiElementBehaviors.class;
        //noinspection rawtypes
        Class actual = behaviors.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of UiElementBehaviors");
    }
}
