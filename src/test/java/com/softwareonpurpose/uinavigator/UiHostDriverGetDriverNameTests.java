package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiHostDriverGetDriverNameTests extends TestClass {
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

