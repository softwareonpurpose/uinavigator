package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiHost;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class UiViewTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExpect() {
        //noinspection rawtypes
        Class expected = MockView.class;
        UiView view = MockView.directNav();
        //noinspection rawtypes
        Class actual = UiView.expect(view.getClass()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
