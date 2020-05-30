package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiHostGetStateTests extends TestClass {
    @Test
    public void testGetCookieValue() {
        UiHost host = UiHost.getInstance();
        MockViewRelativePath.directNav(host);
        String actual = host.getState("NID", "www.google.com", "/");
        host.quit();
        Assert.assertFalse((actual == null || actual.isEmpty()), "Failed to return a cookie value");
    }
}

