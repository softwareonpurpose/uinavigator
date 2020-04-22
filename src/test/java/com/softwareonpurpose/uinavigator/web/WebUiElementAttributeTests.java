package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementAttributeTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetAttribute() {
        final WebUiElement element = WebUiElement.getInstance("Bogus Select", new By.ById("empty-select-two"));
        String expected = "bogus";
        MockView.directNav();
        String actual = element.getAttribute("data-test");
        Assert.assertEquals(actual, expected, "Failed to return attribute value");
    }

    @Test
    public void testSetAttribute() {
        WebUiElement element = WebUiElement.getInstance("Element", new By.ById("name"));
        String expected = "new-value";
        MockView.directNav();
        element.setAttribute("data-test", expected);
        String actual = element.getAttribute("data-test");
        Assert.assertEquals(actual, expected, "Failed to set attribute to new value");
    }
}
