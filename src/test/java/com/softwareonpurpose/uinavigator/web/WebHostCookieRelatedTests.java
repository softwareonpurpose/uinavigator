package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockViewRelativePath;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebHostCookieRelatedTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebHost.quitInstance();
    }

    @Test
    public void testGetCookieValue() {
        MockViewRelativePath.directNav();
        String actual = WebHost.getInstance().getCookieValue("NID", "www.google.com", "/");
        Assert.assertFalse((actual == null || actual.isEmpty()), "Failed to return a cookie value");
    }
}

