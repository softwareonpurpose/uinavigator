package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
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
