package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceTests {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance() {
        UiHost host = UiHost.getInstance();
        Class expected = UiElement.class;
        Class actual = UiElement.getInstance("An Element", UiLocatorType.CLASS, "test", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_id() {
        UiHost host = UiHost.getInstance();
        Class expected = UiElement.class;
        Class actual = UiElement.getInstance("An Element", UiLocatorType.ID, "name", host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

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

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_attribute() {
        UiHost host = UiHost.getInstance();
        final String description = "Select";
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "option";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance(description, locatorType, locatorValue, attribute, attributeValue, host);
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
