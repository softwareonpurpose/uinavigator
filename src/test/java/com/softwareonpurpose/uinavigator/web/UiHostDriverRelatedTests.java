package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.MockView;
import com.softwareonpurpose.uinavigator.TestClass;
import com.softwareonpurpose.uinavigator.UiHost;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiHostDriverRelatedTests extends TestClass {
    @Test
    public void testGetUri() {
        host = UiHost.getInstance();
        String expected = MockView.directNav(host).getAddress();
        String actual = host.getAddress();
        Assert.assertEquals(actual, expected, "Failed to return expected URI");
    }

    @Test
    public void testGetDriverName() {
        host = UiHost.getInstance();
        String expected = "ChromeDriver";
        String actual = host.getDriverName();
        Assert.assertTrue(actual.endsWith(expected), "Failed to return correct driver name");
    }
}

