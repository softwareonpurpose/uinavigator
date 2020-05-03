package com.softwareonpurpose.uinavigator.web;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebUiViewTests {
    @AfterMethod
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExpect() {
        //noinspection rawtypes
        Class expected = MockView.class;
        WebUiView view = MockView.directNav();
        //noinspection rawtypes
        Class actual = WebUiView.expect(view.getClass()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @Test
    public void testLoad_queryString() {
        //noinspection rawtypes
        Class expected = MockView.class;
        WebUiView view = MockView.directNav("?name=value");
        //noinspection rawtypes
        Class actual = WebUiView.expect(view.getClass()).getClass();
        Assert.assertEquals(actual, expected, "Failed to return an instance of WebUiElement");
    }

    @Test
    public void testSwitchTo_iFrame() {
        final MockViewFramed view = MockViewFramed.directNav();
        String expected = "Life Reconciled";
        String actual = view.getSiteTitle();
        Assert.assertEquals(actual, expected, "Failed to successfully switch to iframe");
    }
}
