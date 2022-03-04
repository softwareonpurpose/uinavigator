package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetInstanceTests {
    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance() {
        Class expected = UiElement.class;
        Class actual = UiElement.getInstance("An Element", UiLocatorType.CLASS, "test").getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_id() {
        Class expected = UiElement.class;
        Class actual = UiElement.getInstance("An Element", UiLocatorType.ID, "name").getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_parent() {
        final String locatorValue = "select";
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, locatorValue);
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance("Select", UiLocatorType.TAG, "option", parent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_ordinal() {
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance("Select", UiLocatorType.TAG, "option", 2);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_ordinalParent() {
        final String locatorValue = "select";
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, locatorValue);
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance("Select", UiLocatorType.TAG, "option", 3, parent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_attribute() {
        final String description = "Select";
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "option";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance(description, locatorType, locatorValue, attribute, attributeValue);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_attributeParent() {
        final String parentLocatorValue = "form";
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, parentLocatorValue);
        final String description = "Select";
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "option";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance(description, locatorType, locatorValue, attribute, attributeValue, parent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_attributeOrdinal() {
        final String description = "Select";
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "option";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        Integer ordinal = 2;
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance(description, locatorType, locatorValue, attribute, attributeValue, ordinal);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testGetInstance_attributeOrdinalParent() {
        final String parentLocatorValue = "form";
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, parentLocatorValue);
        final String description = "Select";
        final String locatorType = UiLocatorType.TAG;
        final String locatorValue = "option";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        Integer ordinal = 2;
        Class expected = UiElement.class;
        final UiElement element =
                UiElement.getInstance(
                        description, locatorType, locatorValue, attribute, attributeValue, ordinal, parent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
