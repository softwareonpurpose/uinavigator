package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiViewLoadTests {
    @AfterMethod
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testLoad_queryString() {
        //noinspection rawtypes
        Class expected = MockView.class;
        UiView view = MockView.directNav("?name=value");
        //noinspection rawtypes
        Class actual = UiView.expect(view.getClass()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
