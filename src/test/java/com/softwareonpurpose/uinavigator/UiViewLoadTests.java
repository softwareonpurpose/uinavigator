package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiViewLoadTests extends TestClass {
    @Test
    public void testLoad_queryString() {
        host = UiHost.getInstance();
        //noinspection rawtypes
        Class expected = MockView.class;
        UiView view = MockView.directNav("?name=value", host);
        //noinspection rawtypes
        Class actual = UiView.expect(view.getClass(), host).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }
}
