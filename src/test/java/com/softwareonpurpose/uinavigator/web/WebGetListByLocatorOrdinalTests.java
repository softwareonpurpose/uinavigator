package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute() {
        MockView.directNav();
        Integer expected = 1;
        Integer actual = WebGetListByLocatorOrdinal.getInstance(UiLocatorType.TAG, "option", 3).execute().size();
        Assert.assertEquals(actual, expected, "Failed to return list of one instance of WebUiElement");
    }

    @Test
    public void testExecute_nonExistent() {
        MockView.directNav();
        Integer expected = 0;
        Integer actual = WebGetListByLocatorOrdinal.getInstance(UiLocatorType.TAG, "label", 3).execute().size();
        Assert.assertEquals(actual, expected, "Failed to return empty list of WebUiElement");
    }
}
