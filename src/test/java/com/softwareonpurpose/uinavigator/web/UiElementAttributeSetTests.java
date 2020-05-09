package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementAttributeSetTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testSetAttribute() {
        UiElement element = UiElement.getInstance("Element", UiLocatorType.ID, "name");
        String expected = "new-value";
        MockView.directNav();
        element.setAttribute("data-test", expected);
        String actual = element.getAttribute("data-test");
        Assert.assertEquals(actual, expected, "Failed to set attribute to new value");
    }
}
