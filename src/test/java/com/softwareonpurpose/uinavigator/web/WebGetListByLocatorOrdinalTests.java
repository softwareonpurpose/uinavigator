package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalTests extends TestClass {@Test
    public void testExecute() {
    host = UiHost.getInstance();
        MockView.directNav(host);
        Integer expected = 1;
        Integer actual = WebGetElementListByLocatorOrdinal.getInstance(UiLocatorType.TAG, "option", 3, UiDriverGet.getInstance()).execute().size();
        Assert.assertEquals(actual, expected, "Failed to return list of one instance of WebUiElement");
    }

    @Test
    public void testExecute_nonExistent() {
        host = UiHost.getInstance();
        MockView.directNav(host);
        Integer expected = 0;
        Integer actual = WebGetElementListByLocatorOrdinal.getInstance(UiLocatorType.TAG, "label", 3, UiDriverGet.getInstance()).execute().size();
        Assert.assertEquals(actual, expected, "Failed to return empty list of WebUiElement");
    }
}
