package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorOrdinalTests extends TestClass {
    @Test
    public void testExecute() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        Integer expected = 1;
        Integer actual = WebGetElementListByLocatorOrdinal.getInstance(UiLocatorType.TAG, "option", 3, host).execute().size();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return list of one instance of WebUiElement");
    }

    @Test
    public void testExecute_nonExistent() {
        UiHost host = UiHost.getInstance();
        MockView.directNav(host);
        Integer expected = 0;
        Integer actual = WebGetElementListByLocatorOrdinal.getInstance(UiLocatorType.TAG, "label", 3, host).execute().size();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return empty list of WebUiElement");
    }
}
