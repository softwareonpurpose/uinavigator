package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiElementGetListNonExistentParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetList_nonExistentParent() {
        int expected = 0;
        UiElement parent = UiElement.getInstance("Parent", UiLocatorType.TAG, "bogus");
        MockView.directNav();
        final String description = "Element List";
        final String locatorValue = "select";
        int actual = UiElement.getList(description, UiLocatorType.TAG, locatorValue, parent).size();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
