package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetListTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetList() {
        int expected = 5;
        WebUiElement parent = WebUiElement.getInstance("Parent", UiLocatorType.TAG, "body");
        MockView.directNav();
        final String elementList = "Element List";
        final String locatorValue = "select";
        int actual = WebUiElement.getList(elementList, UiLocatorType.TAG, locatorValue, parent).size();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
