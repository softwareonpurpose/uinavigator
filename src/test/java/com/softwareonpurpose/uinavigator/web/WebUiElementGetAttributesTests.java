package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetAttributesTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetAttribute() {
        final WebUiElement element = WebUiElement.getInstance("Bogus Select", UiLocatorType.ID, "empty-select-two");
        String expected = "bogus";
        MockView.directNav();
        String actual = element.getAttribute("data-test");
        Assert.assertEquals(actual, expected, "Failed to return attribute value");
    }

    @Test
    public void testGetHref() {
        String expected = "http://www.google.com/";
        MockView.directNav();
        String actual = WebUiElement.getInstance("'Link' element", UiLocatorType.TAG, "a").getHref();
        Assert.assertEquals(actual, expected, "Failed to return HREF of element");
    }
}
