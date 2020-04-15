package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiNavigatorConfigurationTests {
    @Test
    public void testConstructor_nonexistentPropertiesFile() {
        long expected = 3;
        long actual = new UiNavigatorConfiguration("non-existent.properties").getTimeout();
        String message = "Failed to set timeout to default when properties file missing";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testGetInstance() {
        long expected = 3;
        long actual = UiNavigatorConfiguration.getInstance().getTimeout();
        String message = "Failed to set timeout to default when getInstance() called";
        Assert.assertEquals(actual, expected, message);
    }

    @Test
    public void testGetInstance_alreadyInstantiated() {
        long expected = 3;
        UiNavigatorConfiguration.getInstance();
        long actual = UiNavigatorConfiguration.getInstance().getTimeout();
        String message = "Failed to set timeout to default when getInstance() called";
        Assert.assertEquals(actual, expected, message);
    }
}
