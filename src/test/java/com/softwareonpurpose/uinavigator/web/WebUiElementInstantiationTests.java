package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementInstantiationTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetInstance_parent() {
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "body");
        Class<WebUiElement> expected = WebUiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        Class actual = WebUiElement.getInstance(description, UiLocatorType.ID, locatorValue, getParent).getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_ordinal() {
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "body");
        Class<WebUiElement> expected = WebUiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        final int ordinal = 1;
        final WebUiElement element =
                WebUiElement.getInstance(description, UiLocatorType.ID, locatorValue, ordinal, getParent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }

    @Test
    public void testGetInstance_attribute() {
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "body");
        Class<WebUiElement> expected = WebUiElement.class;
        final String description = "Element";
        final String locatorValue = "name";
        final String attribute = "data-test";
        final String attributeValue = "initial";
        final WebUiElement element =
                WebUiElement.getInstance(
                        description, UiLocatorType.ID, locatorValue, attribute, attributeValue, getParent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return instance of WebUiElement");
    }
}
