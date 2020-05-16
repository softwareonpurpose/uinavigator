package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiViewTests extends TestClass {
    @Test
    public void testExpect() {
        UiHost host = UiHost.getInstance();
        //noinspection rawtypes
        Class expected = MockView.class;
        UiView view = MockView.directNav(host);
        //noinspection rawtypes
        Class actual = UiView.expect(view.getClass(), host).getClass();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
