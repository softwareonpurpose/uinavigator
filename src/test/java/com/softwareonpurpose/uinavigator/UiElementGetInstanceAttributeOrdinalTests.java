package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceAttributeOrdinalTests {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_attributeOrdinal() {
        UiHost host = UiHost.getInstance();
        final String description = "Select";
        final UiLocatorType locatorType = UiLocatorType.TAG;
        final String locatorValue = "option";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        Integer ordinal = 2;
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance(description, locatorType, locatorValue, attribute, attributeValue, ordinal, host);
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
