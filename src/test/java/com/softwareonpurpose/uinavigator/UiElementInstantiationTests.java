package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementInstantiationTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetInstance_parent() {
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body");
        Class<UiElement> expected = UiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        Class actual = UiElement.getInstance(description, UiLocatorType.ID, locatorValue, parent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_ordinal() {
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body");
        Class<UiElement> expected = UiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        final int ordinal = 1;
        final UiElement element =
                UiElement.getInstance(description, UiLocatorType.ID, locatorValue, ordinal, parent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_attribute() {
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body");
        Class<UiElement> expected = UiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final UiElement element =
                UiElement.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, parent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }
}
