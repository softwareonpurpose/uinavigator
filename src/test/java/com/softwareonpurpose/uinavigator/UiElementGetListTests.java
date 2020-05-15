package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetListTests extends TestClass {
    @Test
    public void testGetList() {
        host = UiHost.getInstance();
        int expected = 5;
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "body");
        MockView.directNav(host);
        final String elementList = "Element List";
        final String locatorValue = "select";
        int actual = UiElement.getList(elementList, UiLocatorType.TAG, locatorValue, parent).size();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
