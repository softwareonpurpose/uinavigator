package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementGetListTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetList() {
        int expected = 5;
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body");
        MockView.directNav();
        final String elementList = "Element List";
        final String locatorValue = "select";
        int actual = UiElement.getList(elementList, UiLocatorType.TAG, locatorValue, parent).size();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
