package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockViewRelativePath;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiHostGetStateTests extends TestClass {
    @Test
    public void testGetCookieValue() {
        host = UiHost.getInstance();
        MockViewRelativePath.directNav(host);
        String actual = host.getState("NID", "www.google.com", "/");
        Assert.assertFalse((actual == null || actual.isEmpty()), "Failed to return a cookie value");
    }
}

