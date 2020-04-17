package com.softwareonpurpose.uinavigator.web;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class WebGetListByLocatorNonExistentTests {
    @AfterMethod(alwaysRun = true)
    public void terminate() {
        WebUiHost.quitInstance();
    }

    @Test
    public void testExecute_nonexistent() {
        int expected = 0;
        int actual = WebGetListByLocator.getInstance(new By.ById("nonexistent")).execute().size();
        Assert.assertEquals(actual, expected, "Failed to return an empty Collection");
    }
}
