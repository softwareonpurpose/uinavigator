package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiElementGetListNonExistentParentTests extends TestClass {
    @Test
    public void testGetList_nonExistentParent() {
        UiHost host = UiHost.getInstance();
        int expected = 0;
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "bogus", host);
        MockView.directNav(host);
        final String description = "Element List";
        final String locatorValue = "select";
        int actual = UiElement.getList(description, UiLocatorType.TAG, locatorValue, parent, host).size();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
