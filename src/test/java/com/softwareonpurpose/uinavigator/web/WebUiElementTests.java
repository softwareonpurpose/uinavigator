package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetText() {
        String expected = "google";
        MockView.directNav();
        String actual = WebUiElement.getInstance("Element", new By.ByTagName("a")).getText();
        Assert.assertEquals(actual, expected, "Failed to return text value of WebUiElement");
    }

    @Test
    public void testSuppressLogging() {
        boolean expected = true;
        WebUiElement.suppressLogging(true);
        boolean actual = WebUiElement.isLoggingSuppressed();
        //noinspection ConstantConditions
        Assert.assertEquals(actual, expected, "Failed to set log suppression to true");
    }

    @Test
    public void testToString() {
        String expected = "{\"description\":\"Element\",\"behaviors\":{\"getElement\":{\"locator\":{\"tagName\":\"body\"}}}}";
        String actual = WebUiElement.getInstance("Element", new By.ByTagName("body")).toString();
        Assert.assertEquals(actual, expected, "Failed to return the String description");
    }

    @Test
    public void testGetInstanceByLocatorAttributeOrdinalParent() {
        Class expected = WebUiElement.class;
        WebGetElementBehavior getParent = WebGetElementByLocator.getInstance(new By.ByTagName("form"));
        final String description = "Element";
        final By.ByTagName locator = new By.ByTagName("select");
        final String attribute = "data-test";
        final String attributeValue = "select-element";
        final int ordinal = 2;
        final WebUiElement element =
                WebUiElement.getInstance(description, locator, attribute, attributeValue, ordinal, getParent);
        Class actual = element.getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
