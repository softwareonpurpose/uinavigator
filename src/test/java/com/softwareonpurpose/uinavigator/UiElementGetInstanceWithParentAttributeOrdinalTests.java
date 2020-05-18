package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceWithParentAttributeOrdinalTests extends TestClass {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_attributeOrdinalParent() {
        UiHost host = UiHost.getInstance();
        final String parentLocatorValue = "form";
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, parentLocatorValue, host);
        final String description = "Select";
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "option";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        Integer ordinal = 2;
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal, parent, host);
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
