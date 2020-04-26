package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiElementGetListNonExistentParentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testGetList_nonExistentParent() {
        int expected = 0;
        WebGetElementByLocator getParent = WebGetElementByLocator.getInstance(UiLocatorType.TAG, "bogus");
        MockView.directNav();
        int actual = WebUiElement.getList("Element List", UiLocatorType.TAG, "select", getParent).size();
        Assert.assertEquals(actual, expected, "Failed to return a list of WebUiElement");
    }
}
