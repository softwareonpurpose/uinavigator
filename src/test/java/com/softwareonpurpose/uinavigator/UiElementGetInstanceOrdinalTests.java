package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceOrdinalTests {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_ordinal() {
        UiHost host = UiHost.getInstance();
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance("Select", UiLocatorType.TAG, "option", 2, host);
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
