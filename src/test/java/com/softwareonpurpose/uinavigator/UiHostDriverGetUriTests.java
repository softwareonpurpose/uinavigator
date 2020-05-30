package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiHostDriverGetUriTests extends TestClass {
    @Test
    public void testGetUri() {
        UiHost host = UiHost.getInstance();
        String expected = MockView.directNav(host).getAddress();
        String actual = host.getAddress();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected URI");
    }
}

