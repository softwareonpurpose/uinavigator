package com.softwareonpurpose.uinavigator;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UiHostLoadTests extends TestClass {
    @Test
    public void testLoad() {
        UiHost host = UiHost.getInstance();
        String address = "file:///C:/Users/craig/Documents/git/uinavigator/src/test/resources/MockPage.html";
        //noinspection UnnecessaryLocalVariable
        String expected = address;
        host.load(address);
        String actual = host.getAddress();
        host.quit();
        Assert.assertEquals(actual, expected);
    }
}
