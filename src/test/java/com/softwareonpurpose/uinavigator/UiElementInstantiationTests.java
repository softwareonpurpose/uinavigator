package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementInstantiationTests {

    @Test
    public void testGetInstance_parent() {
        UiHost host = UiHost.getInstance();
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body", host);
        Class<UiElement> expected = UiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        //noinspection rawtypes
        Class actual = UiElement.getInstance(description, UiLocatorType.ID, locatorValue, parent, host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_ordinal() {
        UiHost host = UiHost.getInstance();
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body", host);
        Class<UiElement> expected = UiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        final int ordinal = 1;
        final UiElement element =
                UiElement.getInstance(description, UiLocatorType.ID, locatorValue, ordinal, parent, host);
        //noinspection rawtypes
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_attribute() {
        UiHost host = UiHost.getInstance();
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body", host);
        Class<UiElement> expected = UiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final UiElement element =
                UiElement.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, parent, host);
        //noinspection rawtypes
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }
}
