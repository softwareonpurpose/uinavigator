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
        UiHost host = UiHost.getInstance();
        String expected = MockView.directNav(host).getAddress();
        String actual = host.getAddress();
        host.quit();
        Assert.assertEquals(actual, expected, "Failed to return expected URI");
    }

    @Test
    public void testGetDriverName() {
        UiHost host = UiHost.getInstance();
        String expected = "RemoteWebDriver";
        String actual = host.getDriverName();
        host.quit();
        final String messageFormat = "Failed to return correct driver name, expected: [%s], actual: [%s}";
        final String message = String.format(messageFormat, expected, actual);
        Assert.assertTrue(actual.endsWith(expected), message);
    }
}

