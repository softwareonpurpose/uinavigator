package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementTests extends TestClass {
    @Test
    public void testGetText() {
        UiHost host = UiHost.getInstance();
        String expected = "google";
        MockView.directNav(host);
        String actual = UiElement.getInstance("Element", UiLocatorType.TAG, "a", host).getText();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return text value of WebUiElement");
    }

    @Test
    public void testSuppressLogging() {
        boolean expected = true;
        UiElement.suppressLogging(true);
        boolean actual = UiElement.isLoggingSuppressed();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to set log suppression to true");
    }

    @Test
    public void testToString() {
        UiHost host = UiHost.getInstance();
        String expected =
                "{\"description\":\"Element\",\"behaviors\":{\"description\":\"Element\",\"getElement\":{\"locator\":{\"tagName\":\"body\"},\"description\":\"Element\"}}}";
        String actual = UiElement.getInstance("Element", UiLocatorType.TAG, "body", host).toString();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return the String description");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        UiHost host = UiHost.getInstance();
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "form", host);
        final String description = "Element";
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final String locatorValue = "select";
        final UiElement element =
                UiElement.getInstance(
                        description, UiLocatorType.TAG, locatorValue, attribute, attributeValue, ordinal, parent, host);
        Class<UiElement> expected = UiElement.class;
        //noinspection rawtypes
        Class actual = element.getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
