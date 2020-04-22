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
}
